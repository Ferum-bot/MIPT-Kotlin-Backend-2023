docker build -t mipt_postgres_bd .
docker run -d -p 9000:5432 --name mipt_postgres_bd mipt_postgres_bd