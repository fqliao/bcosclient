package org.bcosliteclient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Demo {

  public static void main(String[] args) {
    split();

  }

  private static void split() {
    List<Map<String, String>> fields= new ArrayList<Map<String,String>>();
    Map<String, String> field = new HashMap<>();
//    field.put("key_field", "name");
//    field.put("value_field", "sex");
    field.put("key_field", "node_id");
    field.put("value_field", " ");
    fields.add(field);
//    System.out.println(fields.toString());
    String key = fields.get(0).get("key_field");
    String value_field = fields.get(0).get("value_field");
    String[] values = value_field.split(",");
    String table_name = "_sys_miners_";
    String sql = getSql(table_name , key, values);
    System.out.println(sql);
  }

  private static String getSql(String table_name, String key, String[] values) {
    StringBuilder sql = new StringBuilder();
    sql.append("CREATE TABLE IF NOT EXISTS ").append("`").append(table_name).append("`").
    append("(\n").append(" `_id_` int unsigned auto_increment,\n").
    append(" `_hash_` varchar(32) not null,\n").append("  `_num_` int not null,\n").
    append("`_status_` int not null,\n").append("`").append(key).append("`").append(" varchar(256) default '',\n");
    if(!"".equals(values[0].trim()))
    {
      for (String value : values) 
      {
        sql.append(" `").append(value).append("` varchar(2048) default '',\n");
      }
    }
    sql.append(" PRIMARY KEY( `_id_` ),\n").append(" KEY(`").append(key).append("`),\n").
    append(" KEY(`_num_`)\n").append(")ENGINE=InnoDB default charset=utf8mb4;");
    return sql.toString();
  }

}
