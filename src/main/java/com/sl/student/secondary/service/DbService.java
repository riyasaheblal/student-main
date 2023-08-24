package com.sl.student.secondary.service;

import com.sl.student.primary.config.DatasourceConfig;
import com.sl.student.primary.model.DbParams;
import com.sl.student.primary.model.Request;
import com.sl.student.secondary.util.Constants;
import com.zaxxer.hikari.pool.HikariProxyConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.StringJoiner;

import static com.sl.student.secondary.util.Constants.*;

@Service
public class DbService {

    @Autowired
    private DatasourceConfig datasourceConfig;

    public Connection getDbConnection(Request request) {
        Connection conn;
        if (request.ConnectionString() != null || request.ConnectionString().trim().length() == 0) {
            DbParams dbParams = getDbParam(request.ConnectionString());

            conn = datasourceConfig.connect(getUrlForProvider(request.providerType(), dbParams),
                    dbParams.getUsername(), dbParams.getPassword());

        } else {
            //here we will return default connection
            conn = null;
        }

        return conn;
    }

    private String getUrlForProvider(String provider, DbParams dbParams){

        StringBuilder url = new StringBuilder();

        if (provider.toLowerCase().equals(PROVIDER_TYPE_POSTGRESQL))
            url.append(PROVIDER_URL_POSTGRESQL);
        if (provider.toLowerCase().equals(PROVIDER_TYPE_MYSQL))
            url.append(PROVIDER_URL_MYSQL);
        if (provider.toLowerCase().equals(PROVIDER_TYPE_SQLSERVER))
            url.append(PROVIDER_URL_SQLSERVER);
        if (provider.toLowerCase().equals(PROVIDER_TYPE_ORACLE))
            url.append(PROVIDER_URL_ORACLE);

        url.append("//").append(dbParams.getServerHost())
                .append(":").append(dbParams.getPort()).append("/")
                .append(dbParams.getDbName());

        System.out.println("URL :: "+url);

        return url.toString();
    }

    private DbParams getDbParam(String connectionString){
        DbParams dbParams = new DbParams();
        String[] dbStrings = connectionString.split(";");


        for (String str : dbStrings) {
            if (str.toLowerCase().contains(SERVER))
                dbParams.setServerHost(getParamValue(str));
            if (str.toLowerCase().contains(PORT))
                dbParams.setPort(getParamValue(str));
            if (str.toLowerCase().contains(UID))
                dbParams.setUsername(getParamValue(str));
            if (str.toLowerCase().contains(PWD))
                dbParams.setPassword(getParamValue(str));
            if (str.toLowerCase().contains(DATABASE))
                dbParams.setDbName(getParamValue(str));
            if (str.toLowerCase().contains(MAXPOOLSIZE))
                dbParams.setMaximumPoolSize(getParamValue(str));
        }

        return dbParams;
    }

    private String getParamValue(String paramaString){
        String[] paramValue = paramaString.split("=");
        return  paramValue[1];
    }


}
