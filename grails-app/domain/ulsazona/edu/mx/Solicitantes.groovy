package ulsazona.edu.mx

class Solicitantes {
	String nombre
	String apPaterno
	String apMaterno
	String rol
	String matricula
    static constraints = {
    }
    public String toString(){
    	return nombre+" "+apMaterno+" "+ rol;
    }
}