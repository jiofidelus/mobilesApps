package com.example.localisation;

public class Message {

private String numero;
private String message;
private String date;


public Message() {
	super();
	// TODO Auto-generated constructor stub
}


public Message(String numero, String message, String date) {
	super();
	this.numero = numero;
	this.message = message;
	this.date = date;
}


public String getNumero() {
	return numero;
}
public void setNumero(String numero) {
	this.numero = numero;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}


@Override
public String toString() {
	return "Message [numero=" + numero + ", message=" + message + ", date="
			+ date + "]";
}

}
