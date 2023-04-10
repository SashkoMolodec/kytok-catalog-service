Run PostgreSQL
```shell
docker run --name vinyls_catalog -p 5432:5432 -e POSTGRES_USER=user -e POSTGRES_PASSWORD=password -d postgres
```