/*
 * Copyright Dansk Bibliotekscenter a/s. Licensed under GPLv3
 * See license text in LICENSE.txt
 */

package dk.dbc.monitoring.errorlog;

import fish.payara.cloud.connectors.kafka.api.KafkaListener;
import fish.payara.cloud.connectors.kafka.api.OnRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "clientId",
            propertyValue = "${ENV=KAFKA_CLIENT_ID}"),
    @ActivationConfigProperty(propertyName = "groupIdConfig",
            propertyValue = "${ENV=KAFKA_GROUP_ID}"),
    @ActivationConfigProperty(propertyName = "topics",
            propertyValue = "${ENV=KAFKA_TOPICS}"),
    @ActivationConfigProperty(propertyName = "bootstrapServersConfig",
            propertyValue = "${ENV=KAFKA_HOSTS}"),
    @ActivationConfigProperty(propertyName = "autoCommitInterval",
            propertyValue = "100"),
    @ActivationConfigProperty(propertyName = "retryBackoff",
            propertyValue = "1000"),
    @ActivationConfigProperty(propertyName = "keyDeserializer",
            propertyValue = "org.apache.kafka.common.serialization.StringDeserializer"),
    @ActivationConfigProperty(propertyName = "valueDeserializer",
            propertyValue = "org.apache.kafka.common.serialization.StringDeserializer"),
    @ActivationConfigProperty(propertyName = "pollInterval",
            propertyValue = "3000"),
})
public class KafkaMdb implements KafkaListener {
    @OnRecord
    public void onMessage(ConsumerRecord record) {
        // TODO: 13-02-19 filter error logs only and do something intelligent with them
        System.out.println("Got message " + record);
    }
}
