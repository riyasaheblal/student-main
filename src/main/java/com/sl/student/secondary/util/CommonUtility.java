package com.sl.student.secondary.util;

import com.sl.student.primary.model.DbParams;

import static com.sl.student.secondary.util.Constants.*;
import static com.sl.student.secondary.util.Constants.MAXPOOLSIZE;

public class CommonUtility {

    public static DbParams getDbParam(String connectionString){
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

    public static String getParamValue(String paramaString){
        String[] paramValue = paramaString.split("=");
        return  paramValue[1];
    }


}
