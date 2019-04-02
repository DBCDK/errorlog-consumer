/*
 * Copyright Dansk Bibliotekscenter a/s. Licensed under GPLv3
 * See license text in LICENSE.txt
 */

package dk.dbc.monitoring.errorlog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import dk.dbc.monitoring.errorlog.ErrorCause;
import org.slf4j.event.Level;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Optional;

/**
 * This class has been shamelessly copied from the https://github.com/DBCDK/log-tracer project
 * with minor adjustments.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LogEvent {
    private OffsetDateTime timestamp;
    private String host;
    @JsonProperty("sys_env")
    private String env;
    @JsonProperty("sys_team")
    private String team;
    @JsonProperty("sys_appid")
    private String appID;
    @JsonProperty("sys_taskid")
    private String taskId;
    @JsonProperty("sys_type")
    private String type;
    @JsonProperty("sys_json")
    private Boolean json;
    @JsonProperty("stack_trace")
    private String stacktrace;

    private Level level;
    private String message;
    private String thread;
    private String logger;
    private Map<String, String> mdc;

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStacktrace() {
        return stacktrace;
    }

    public void setStacktrace(String stacktrace) {
        this.stacktrace = stacktrace;
    }

    public String getThread() {
        return thread;
    }

    public void setThread(String thread) {
        this.thread = thread;
    }

    public String getLogger() {
        return logger;
    }

    public void setLogger(String logger) {
        this.logger = logger;
    }

    public Map<String, String> getMdc() {
        return mdc;
    }

    public void setMdc(Map<String, String> mdc) {
        this.mdc = mdc;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean isJson() {
        return json;
    }

    public void setJson(Boolean json) {
        this.json = json;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LogEvent logEvent = (LogEvent) o;

        if (timestamp != null ? !timestamp.equals(logEvent.timestamp) : logEvent.timestamp != null) {
            return false;
        }
        if (host != null ? !host.equals(logEvent.host) : logEvent.host != null) {
            return false;
        }
        if (env != null ? !env.equals(logEvent.env) : logEvent.env != null) {
            return false;
        }
        if (team != null ? !team.equals(logEvent.team) : logEvent.team != null) {
            return false;
        }
        if (appID != null ? !appID.equals(logEvent.appID) : logEvent.appID != null) {
            return false;
        }
        if (taskId != null ? !taskId.equals(logEvent.taskId) : logEvent.taskId != null) {
            return false;
        }
        if (type != null ? !type.equals(logEvent.type) : logEvent.type != null) {
            return false;
        }
        if (json != null ? !json.equals(logEvent.json) : logEvent.json != null) {
            return false;
        }
        if (level != logEvent.level) {
            return false;
        }
        if (message != null ? !message.equals(logEvent.message) : logEvent.message != null) {
            return false;
        }
        if (stacktrace != null ? !stacktrace.equals(logEvent.stacktrace) : logEvent.stacktrace != null) {
            return false;
        }
        if (thread != null ? !thread.equals(logEvent.thread) : logEvent.thread != null) {
            return false;
        }
        if (logger != null ? !logger.equals(logEvent.logger) : logEvent.logger != null) {
            return false;
        }
        return mdc != null ? mdc.equals(logEvent.mdc) : logEvent.mdc == null;
    }

    @Override
    public int hashCode() {
        int result = timestamp != null ? timestamp.hashCode() : 0;
        result = 31 * result + (host != null ? host.hashCode() : 0);
        result = 31 * result + (env != null ? env.hashCode() : 0);
        result = 31 * result + (team != null ? team.hashCode() : 0);
        result = 31 * result + (appID != null ? appID.hashCode() : 0);
        result = 31 * result + (taskId != null ? taskId.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (json != null ? json.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (stacktrace != null ? stacktrace.hashCode() : 0);
        result = 31 * result + (thread != null ? thread.hashCode() : 0);
        result = 31 * result + (logger != null ? logger.hashCode() : 0);
        result = 31 * result + (mdc != null ? mdc.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LogEvent{" +
                "timestamp=" + timestamp +
                ", host='" + host + '\'' +
                ", env='" + env + '\'' +
                ", team='" + team + '\'' +
                ", appID='" + appID + '\'' +
                ", taskId='" + taskId + '\'' +
                ", type='" + type + '\'' +
                ", json=" + json +
                ", level=" + level +
                ", message='" + message + '\'' +
                ", stacktrace='" + stacktrace + '\'' +
                ", thread='" + thread + '\'' +
                ", logger='" + logger + '\'' +
                ", mdc=" + mdc +
                '}';
    }

    public ErrorLogEntity toErrorLogEntity() {
        final Optional<ErrorCause> errorCause = ErrorCause.of(this);
        final String cause;
        if (errorCause.isPresent()) {
            cause = errorCause.toString();
        } else {
            cause = "";
        }

        return new ErrorLogEntity()
                .withApp(appID)
                .withNamespace(env)
                .withHost(host)
                .withContainer(taskId)
                .withMessage(message)
                .withTeam(team)
                .withLogger(logger)
                .withCause(cause)
                .withStacktrace(stacktrace)
                .withTimeLogged(timestamp)
                .withContext(mdc);
    }
}
