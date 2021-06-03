docker exec -it kafka-1 /bin/bash
./bin/kafka-topics --create \
    --zookeeper zookeeper:2181 \
    --topic test \
    --partitions 3 \
    --replication-factor 3