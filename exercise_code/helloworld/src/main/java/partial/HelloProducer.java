package partial;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;


public class HelloProducer {
    public void createProducer() {
        long numberOfEvents = 5;

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "broker101:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        for (int i = 0; i < 100000; i++) {
            String key = "firstkey";
            String value = "firstvalue:" + i;

            ProducerRecord<String,String> record = new ProducerRecord<>("offset_file", key, value);
            producer.send(record);
            
            System.out.println("key: " + key + ", value: " + value);

        }
        
        producer.close();

    }

    public static void main(String[] args) {
        HelloProducer helloProducer = new HelloProducer();
        helloProducer.createProducer();
    }
}
