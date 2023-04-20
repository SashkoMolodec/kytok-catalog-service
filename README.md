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
Expose to outside
```shell
kubectl port-forward service/catalog-service 9001:80
```
Stop minikube
```shell
minikube stop --profile kytok
```