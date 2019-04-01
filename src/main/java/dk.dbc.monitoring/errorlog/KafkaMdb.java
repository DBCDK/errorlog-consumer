/*
 * Copyright Dansk Bibliotekscenter a/s. Licensed under GPLv3
 * See license text in LICENSE.txt
 */

package dk.dbc.monitoring.errorlog;

import dk.dbc.monitoring.errorlog.model.LogEvent;
import dk.dbc.monitoring.errorlog.model.LogEventMapper;
import fish.payara.cloud.connectors.kafka.api.KafkaListener;
import fish.payara.cloud.connectors.kafka.api.OnRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.event.Level;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import java.nio.charset.StandardCharsets;

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "clientId",
            propertyValue = "${ENV=KAFKA_CLIENT_ID}"),
    @ActivationConfigProperty(propertyName = "groupIdConfig",
            propertyValue = "${ENV=KAFKA_GROUP_ID}"),
    @ActivationConfigProperty(propertyName = "topics",
            propertyValue = "${ENV=KAFKA_TOPICS}"),
    @ActivationConfigProperty(propertyName = "bootstrapServersConfig",
            propertyValue = "${ENV=KAFKA_HOSTS}"),
    @ActivationConfigProperty(propertyName = "EnableAutoCommit",
            propertyValue = "false"),
    @ActivationConfigProperty(propertyName = "autoOffsetReset",
            propertyValue = "latest"),
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
    private static final LogEventMapper LOG_EVENT_MAPPER = new LogEventMapper();

    @OnRecord
    public void onMessage(ConsumerRecord record) {
        final LogEvent logEvent = LOG_EVENT_MAPPER.unmarshall(
                ((String) record.value()).getBytes(StandardCharsets.UTF_8));

        if (logEvent.getLevel() != null && logEvent.getLevel() == Level.ERROR) {
            // TODO: 14-02-19 persist error log events
            System.out.println(logEvent);
        }
    }
}
