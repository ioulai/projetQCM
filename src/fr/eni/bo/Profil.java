package fr.eni.bo;

public class Profil {

		private int id;
		private String libelle;
		
		public Profil() {
			super();
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getLibelle() {
			return libelle;
		}
		public void setLibelle(String libelle) {
			this.libelle = libelle;
		}
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Profil [id=").append(id).append(", libelle=").append(libelle).append("]");
			return builder.toString();
		}
		
		
		
		
}
