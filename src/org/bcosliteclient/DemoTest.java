package org.bcosliteclient;

import io.bretty.console.table.Alignment;
import io.bretty.console.table.ColumnFormatter;
import io.bretty.console.table.Table;

public class DemoTest {

	public static void main(String[] args) {
		String[] headers = {"table_name", "address", "enable_num"};
		String[][] data = {{"t_test", "0xf1585b8d0e08a0a00fff662e24d67ba95a438256", 1+""}, {"_sys_table_access_", "0xc0d0e6ccc0b44c12196266548bec4a3616160e7d", 34+""}};
		ColumnFormatter<String> cf = ColumnFormatter.text(Alignment.CENTER, 42);
		Table table = Table.of(headers, data, cf);
		System.out.println(table); // NOTICE: table.toString() is called implicitly

	}

}
