### product apis
- GET all products `:8080/api/products`
- GET product `:8080/api/products/{productId}`
- GET product thumbnail `:8080/api/products/thumbnail/{productId}`
- POST, add new product `:8080/api/products/`
- POST, add new thumbnail to product `:8080/api/products/thumbnail/{productId}`
- PUT, update product details: `:8080/api/products/{productId}`
- DELETE product: `:8080/api/products/{productId}`
- PUT, Add supplier to product `:8080/api/products/{productId}/{supplierId}`
- DELETE supplier to product `:8080/api/products/{productId}/{supplierId}`


### supplier apis
- GET all suppliers `:8080/api/supplier`
- GET supplier `:8080/api/supplier/{supplierId}`
- POST, add new supplier `:8080/api/supplier`
- DELETE supplier `:8080/api/supplier/{supplierId}`
- PUT, edit supplier name `:8080/api/supplier/{supplierId}`

## Models

- Product
- Supplier


## Steps to start in local with docker
1. mvn clean install
2. This will generate jar in target.
3. Now start Dockerfile which starts the app in 8080 port.
4. Run docker compose file with docker-compose build & docker-compose up.
5. Mysql container will be created.
