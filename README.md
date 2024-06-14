# fs2-kafka-App

## Stack
- Http4s
- ember server
- fs2-kafka
- ciris for configuration
- Circe
## 

### Listeners

When a client (producer/consumer) starts, it will request metadata about which broker is the leader for a partition—and it can do this from any broker. The metadata returned will include the endpoints available for the Leader broker for that partition, and the client will then use those endpoints to connect to the broker to read/write data as required

You need to tell Kafka how the brokers can reach each other, but also make sure that external clients (producers/consumers) can reach the broker they need to

A listener is a combination of
- Host/IP
- Port
- Protocol


```bash
KAFKA_LISTENERS: LISTENER_BOB://kafka0:29092,LISTENER_FRED://localhost:9092
KAFKA_ADVERTISED_LISTENERS: LISTENER_BOB://kafka0:29092,LISTENER_FRED://localhost:9092
KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: LISTENER_BOB:PLAINTEXT,LISTENER_FRED:PLAINTEXT
KAFKA_INTER_BROKER_LISTENER_NAME: LISTENER_BOB
```

If you want to use SSL, you need to include SSL in your listener name (e.g. LISTENER_BOB_SSL).

Kafka brokers communicate between themselves, usually on the internal network (e.g. Docker network, AWS VPC, etc). To define which listener to use, specify KAFKA_INTER_BROKER_LISTENER_NAME (inter.broker.listener.name). The host/IP used must be accessible from the broker machine to others.

Kafka clients may well not be local to the broker’s network, and this is where the additional listeners come in.

[kafka-listeners-explained](https://rmoff.net/2018/08/02/kafka-listeners-explained/)

[kafka-listeners](https://levelup.gitconnected.com/kafka-listeners-c0ea09f130e4)

```bash
listeners=PLAINTEXT://0.0.0.0:9092
advertised.listeners=PLAINTEXT://external-host:9092

```

### Traffic Flow:
The broker listens on all interfaces within the Docker container on port 9092.
The broker advertises external-host:9092 (an external address) to clients.
Clients connect to `external-host:9092`.
Docker or Kubernetes handles the traffic routing from the external address to the broker's internal address.
Internally, the broker receives traffic on port 9092 and handles it as if it were connecting on `0.0.0.0:9092`


[kafka-networking-via-wireshark](https://vkontech.com/kafka-networking-via-wireshark/)