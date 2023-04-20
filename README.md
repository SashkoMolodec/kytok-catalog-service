Run PostgreSQL
```shell
docker run --name vinyldb_catalog -p 5432:5432 -e POSTGRES_USER=user -e POSTGRES_PASSWORD=password -d postgres
```

#### Kubernetes
Run minikube local cluster
```shell
minikube start --cpus 2 --memory 4g --driver docker --profile kytok
```
Run postgresSQL in deployment/kubernetes/platform/development
```shell
kubectl apply -f services
```
Create Deployment and Service objects for catalog service
```shell
kubectl apply -f k8s/deployment.yml
kubectl apply -f k8s/service.yml
```
Load image into minikube
```shell
minikube image load edge-service --profile kytok
```
Expose to outside (single service)
```shell
kubectl port-forward service/catalog-service 9001:80
```
Expose to outside the whole cluster (127.0.0.1)
```shell
minikube tunnel --profile kytok
```
Stop minikube
```shell
minikube stop --profile kytok
```
