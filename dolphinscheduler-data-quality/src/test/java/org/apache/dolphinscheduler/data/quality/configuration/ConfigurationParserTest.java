/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.dolphinscheduler.data.quality.configuration;

import org.apache.dolphinscheduler.data.quality.utils.JsonUtils;

import org.junit.Assert;
import org.junit.Test;

/**
 * ConfigurationParserTest
 */
public class ConfigurationParserTest {

    @Test
    public void testConfigurationValidate() {
        Assert.assertEquals(1,verifyConfigurationValidate());
    }

    private int verifyConfigurationValidate() {
        int flag = 1;
        try {
            String parameterStr = "{\n"
                    + "\t\"name\": \"\\u81EA\\u5B9A\\u4E49SQL\",\n"
                    + "\t\"connectors\": [{\n"
                    + "\t\t\"type\": \"JDBC\",\n"
                    + "\t\t\"config\": {\n"
                    + "\t\t\t\"database\": \"test\",\n"
                    + "\t\t\t\"password\": \"123456\",\n"
                    + "\t\t\t\"driver\": \"com.mysql.jdbc.Driver\",\n"
                    + "\t\t\t\"user\": \"test\",\n"
                    + "\t\t\t\"table\": \"test1\",\n"
                    + "\t\t\t\"url\": \"jdbc:mysql://localhost:3306/test\"\n"
                    + "\t\t}\n"
                    + "\t}],\n"
                    + "\t\"writers\": [{\n"
                    + "\t\t\"type\": \"JDBC\",\n"
                    + "\t\t\"config\": {\n"
                    + "\t\t\t\"database\": \"dolphinscheduler\",\n"
                    + "\t\t\t\"password\": \"Test@123!\",\n"
                    + "\t\t\t\"driver\": \"com.mysql.jdbc.Driver\",\n"
                    + "\t\t\t\"user\": \"test\",\n"
                    + "\t\t\t\"table\": \"t_ds_dqs_result\",\n"
                    + "\t\t\t\"url\": \"jdbc:mysql://localhost:3306/dolphinscheduler?characterEncoding=UTF-8&allowMultiQueries=true\",\n"
                    + "\t\t\t\"sql\": \"SELECT 1 as rule_type,"
                    + "'\\u81EA\\u5B9A\\u4E49SQL' as rule_name,"
                    + "18 as process_definition_id,"
                    + "64 as process_instance_id,"
                    + "70 as task_instance_id,"
                    + "mySum AS statistics_value, "
                    + "total_count.total AS comparison_value,"
                    + "0 as check_type,"
                    + "6 as threshold, "
                    + "0 as operator, "
                    + "0 as failure_strategy, "
                    + "'2021-01-31 15:00:07' as create_time,"
                    + "'2021-01-31 15:00:07' as update_time from ( select sum(c4) as mySum from test1 ) tmp1 join total_count\"\n"
                    + "\t\t}\n"
                    + "\t}],\n"
                    + "\t\"executors\": [{\n"
                    + "\t\t\"index\": \"1\",\n"
                    + "\t\t\"execute.sql\": \"SELECT COUNT(*) AS total FROM test1 WHERE (c3 != '55')\",\n"
                    + "\t\t\"table.alias\": \"total_count\"\n"
                    + "\t}]\n"
                    + "}";
            DataQualityConfiguration dataQualityConfiguration = JsonUtils.fromJson(parameterStr,DataQualityConfiguration.class);
            dataQualityConfiguration.validate();
        } catch (Exception e) {
            flag = 0;
            e.printStackTrace();
        }
        return flag;
    }
}