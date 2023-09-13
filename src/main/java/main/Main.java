package main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import domen.Putnik;
import so.putnik.SOUcitajPutnike;

public class Main {
	
	public static void main(String[] args) {

		try(FileWriter file = new FileWriter("putnici.json")){
			ArrayList<Putnik> putnici = new ArrayList<>();

			SOUcitajPutnike so = new SOUcitajPutnike();
			so.execute(new Putnik());

			putnici = so.getLista();
			Gson gson = new GsonBuilder().setPrettyPrinting().create();

			String json = gson.toJson(putnici);
         	file.write(json);

		} catch (Exception e) {
			// TODO: handle exception
		}





	}

}
