/*
 * Copyright Dansk Bibliotekscenter a/s. Licensed under GPLv3
 * See license text in LICENSE.txt
 */

package dk.dbc.monitoring.errorlog.model;

import org.junit.Test;

import java.nio.charset.StandardCharsets;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LogEventMapperTest {
    private final LogEventMapper logEventMapper = new LogEventMapper();

    @Test
    public void unmarshallUnknownField() {
        final String json = "{\"host\":\"localhost\", \"hostess\":\"localhostess\"}";
        logEventMapper.unmarshall(json.getBytes(StandardCharsets.UTF_8));
    }

    @Test
    public void mappings() {
        final String json =
                "{\"level\":\"FATAL\"," +
                 "\"sys_nydus_destination\":\"k8s-metascrum-prod\"," +
                 "\"logger\":\"dk.dbc.dataio.sink.batchexchange.ScheduledBatchFinalizerBean\"," +
                 "\"sys_appid\":\"metascrum-prod/dataio-batch-exchange-sink-boblebad-service\"," +
                 "\"thread\":\"__ejb-thread-pool8\"," +
                 "\"message\":\"Finalized 0 batches\"," +
                 "\"sys_kubernetes_ns\":\"metascrum-prod\"," +
                 "\"version\":1," +
                 "\"sys_stream\":\"stdout\"," +
                 "\"sys_kubernetes_container\":\"dataio-batch-exchange-sink-boblebad-service\"," +
                 "\"@timestamp\":\"2019-05-20T08:48:00.001+02:00\"," +
                 "\"sys_env\":\"kubernetes\"," +
                 "\"HOSTNAME\":\"dataio-batch-exchange-sink-boblebad-service-5545b64558-gcm6t\"," +
                 "\"level_value\":20000," +
                 "\"sys_host\":\"container-p13\"," +
                 "\"sys_kubernetes\":{" +
                        "\"container\":{" +
                            "\"name\":\"dataio-batch-exchange-sink-boblebad-service\"}," +
                        "\"labels\":{" +
                            "\"network-policy-http-outgoing\":\"yes\"," +
                            "\"network-policy-http-incoming\":\"yes\"," +
                            "\"network-policy-postgres-outgoing\":\"yes\"," +
                            "\"network-policy-payara-incoming\":\"yes\"," +
                            "\"network-policy-mq-outgoing\":\"yes\"," +
                            "\"app_dbc_dk/team\": \"metascrum\"," +
                            "\"pod-template-hash\":\"5545b64558\"" +
                        "}," +
                        "\"namespace\":\"metascrum-prod\"," +
                        "\"pod\":{" +
                            "\"name\":\"dataio-batch-exchange-sink-boblebad-service-5545b64558-gcm6t\"," +
                            "\"uid\":\"5b6b4092-77c8-11e9-b705-48df373baf30\"}," +
                        "\"replicaset\":{" +
                            "\"name\":\"dataio-batch-exchange-sink-boblebad-service-5545b64558\"}" +
                 "}," +
                 "\"sys_taskid\":\"metascrum-prod/dataio-batch-exchange-sink-boblebad-service-5545b64558-gcm6t\"," +
                 "\"timestamp\":\"2019-05-20T08:48:00.001+02:00\"}";
        final LogEvent logEvent = logEventMapper.unmarshall(json.getBytes(StandardCharsets.UTF_8));
        assertThat("team",logEvent.getKubernetes().getTeam(), is("metascrum"));
        assertThat("marshall->unmarshall",
                logEventMapper.unmarshall(logEventMapper.marshall(logEvent).getBytes(StandardCharsets.UTF_8)),
                is(logEvent));
    }
}