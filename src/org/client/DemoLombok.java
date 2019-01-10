package org.client;

import lombok.Getter;
import lombok.Setter;

public class DemoLombok {

	public static void main(String[] args) {
			
		AliPayBean ali = new AliPayBean();

	}

}



@SuppressWarnings("unused")
class AliPayBean {
    @Setter
    @Getter
    private String sellerId;
}

