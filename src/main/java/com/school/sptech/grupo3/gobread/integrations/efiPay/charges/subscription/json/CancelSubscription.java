package com.school.sptech.grupo3.gobread.integrations.efiPay.charges.subscription.json;

import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;
import com.school.sptech.grupo3.gobread.integrations.efiPay.Credentials;
import org.json.JSONObject;

import java.util.HashMap;

public class CancelSubscription {
	public static void main(String[] args) {
		/* *********  Set credential parameters ******** */

		Credentials credentials = new Credentials();

		JSONObject options = new JSONObject();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("sandbox", credentials.isSandbox());

		/* ************************************************* */ 
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", "906157");

		try {
		    EfiPay efi = new EfiPay(options);
		    JSONObject response = efi.call("cancelSubscription", params, new JSONObject());
		    System.out.println(response);
		}catch (EfiPayException e){
		    System.out.println(e.getCode());
		    System.out.println(e.getError());
		    System.out.println(e.getErrorDescription());
		}catch (Exception e) {
		    System.out.println(e.getMessage());
		}
	}
}
