package com.example.virtualworkrooms;

import java.util.ArrayList;

import com.example.virtualworkrooms.modelo.Categoria;
import com.example.virtualworkrooms.persistencia.CategoriasRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VirtualWorkRoomsApp implements CommandLineRunner{
	@Autowired
	private CategoriasRepositorio categoriasRepo;
	
	public static void main(final String[] args) {
		SpringApplication.run(VirtualWorkRoomsApp.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception{
		System.out.println(categoriasRepo == null);

		ArrayList<Categoria> cats = (ArrayList<Categoria>)categoriasRepo.findAll();
		System.out.println(cats.size());
		for(final Categoria c : cats){
			System.out.println(c.toString());
		} 
	}

}
