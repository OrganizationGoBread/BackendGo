package br.com.efi.open_finance.payment.map;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.school.sptech.grupo3.gobread.integrations.efiPay.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class OfStartPixPayment {
    public static void main(String[] args) {
		Credentials credentials = new Credentials();

		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("certificate", credentials.getCertificate());
		options.put("sandbox", credentials.isSandbox());

		options.put("x-idempotency-key", "ae71713f-875b-4af3-9d85-0bcb43288847");

    	Map<String, Object> body = new HashMap<String, Object>();	
		body.put("pagador", new JSONObject().put("idParticipante", "9f4cd202-8f2b-11ec-b909-0242ac120002")
			.put("cpf", "45204392050").put("cnpj", "90293071000112"));
		body.put("favorecido", new JSONObject().put("contaBanco", new JSONObject()
			.put("codigoBanco", "364")
			.put("agencia", "0001")
			.put("documento", "11122233344")
			.put("nome", "Luiz Silva")
			.put("conta", "654984")
			.put("tipoConta", "CACC")));
		body.put("valor", "9.90");
		body.put("codigoCidadeIBGE", "3540000");
		body.put("infoPagador", "Compra dia xx");

		try {
			EfiPay efi = new EfiPay(options);
			
			Map<String, Object> response = efi.call("ofStartPixPayment", new HashMap<String,String>(), body);
			System.out.println(response);
		}catch (EfiPayException e){
			System.out.println(e.getError());
			System.out.println(e.getErrorDescription());
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }
}
