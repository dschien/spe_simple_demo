# Kubernetes deployment

## 0. Set up you K8S cluster

Enable DNS add-on, to use DNS names to services

Define PG passwords in `PG_CONFIG.txt` according to  `PG_CONFIG.template.txt`
The run `kubectl apply -k .`
[more here](https://kubernetes.io/docs/concepts/configuration/secret/#using-secrets)

## 2. Create a postgres service

step by step instructions [here](https://severalnines.com/database-blog/using-kubernetes-deploy-postgresql)
- include a service, as this enables networking

Run

`kubectl apply -f `

## 3. Create a deployment and service for the backend servers

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: spe-demo-depl
  labels:
    app: spe-demo
spec:
  replicas: 1
  selector:
    matchLabels:
      app: spe-demo
  template:
    metadata:
      labels:
        app: spe-demo
    spec:
      containers:
      - name: spe-demo
        image: dschien/spe-simple-demo
        resources:
          requests:
            cpu: "100m"
        imagePullPolicy: IfNotPresent #Always
        ports:
          # inside container
        - containerPort: 8080
        env:
        - name: SPRING_PROFILES_ACTIVE
          value: "prod"
        - name: db_password
          value: "xxxx"
        - name: dbhost
          value: "postgres"
```

and 

```yaml
apiVersion: v1
kind: Service
metadata:
  name: demo-service
  labels:
    app: spe-demo
spec:
  type: NodePort
  ports:
    # outside
  - port: 8080
    name: http
  selector:
    app: spe-demo
```



## Prepare docker container and push to docker hub registry
In order for spring boot to find our PG containers, we set up a service.

We also need to get the hostname for the postgres service.
"Kubernetes supports 2 primary modes of finding a Service - environment variables and DNS. The former works out of the box while the latter requires the CoreDNS cluster addon."
-- see [here](https://kubernetes.io/docs/concepts/services-networking/connect-applications-service/#accessing-the-service)



We have this add on, so can do this by starting the pg service before the spring service, as service ports are 
passed into nodes as [env vars](https://kubernetes.io/docs/concepts/containers/container-environment-variables/#container-environment).   

This way, we can refer to the service name in the spring deployment container env section.


# Secrets
https://kubectl.docs.kubernetes.io/pages/app_management/secrets_and_configmaps.html

@Todo
use generators to create configmaps and secrets from the same env files

# Autoscale

`kubectl autoscale deployment foo --min=2 --max=10   `  


# Ingress
enable ingress add-on

- no need to work with high port numbers
- single point of entry
- tls termination 

[docs](https://kubernetes.github.io/ingress-nginx/deploy/)

