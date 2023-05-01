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

#### Keycloak
Log in console
```shell
./kcadm.sh config credentials --server http://localhost:8080 --realm master --user user --password password
```
Create realm
```shell
./kcadm.sh create realms -s realm=KytokRealm -s enabled=true
```
Create roles
```shell
./kcadm.sh create roles -r KytokRealm -s name=customer
```
Create users
```shell
./kcadm.sh create users -r KytokRealm -s username=sasha -s firstName=Sasha -s lastName=Krav -s enabled=true
```
Add roles
```shell
./kcadm.sh add-roles -r KytokRealm --uusername sasha --rolename employee --rolename customer
```
Set password
```shell
./kcadm.sh set-password -r KytokRealm --username sasha --new-password password
```
Create Client
```shell
./kcadm.sh create clients -r KytokRealm -s clientId=edge-service -s publicClient=false -s secret=kytok-keycloak-secret -s 'redirectUris=["http://localhost:9000","http://localhost:9000/login/oauth2/code/*"]'
```