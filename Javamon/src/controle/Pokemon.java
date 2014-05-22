package controle;
/**
 * 
 * @author Elton e Flavia <br>
 *<p>
 *Classe que guarda os atributos do pokemon
 *</p>
 *
 */
public class Pokemon {
	
	int ID;
	int vida;
	int ataque;
	int defesa;
	int velocidade;
	Ataque a,b,c,d;
	int[] vantagem=new int[2];
	int[] desvantagem=new int[2];
	/**
	 * Retorna um vetor com os ID's dos pokemons que esse pokemon leva vantagem
	 * @return vetor de ID's de vantagem
	 */
	public int[] getVantagem(){
		return this.vantagem;
	}
	/**
	 * Retorna um vetor com os ID's dos pokemons que esse pokemon leva desvantagem
	 * @return vetor de ID's de desvantagem
	 */
	public int[] getDesvantagem(){
		return this.desvantagem;
	}
	/**
	 * Retorna objeto ataque A desse pokemon, o ataque A e o primeiro ataque
	 * @return Objeto ataque
	 */
	public Ataque getA() {
		return a;
	}

	/**
	 * Retorna objeto ataque B desse pokemon, o ataque B e o segundo ataque
	 * @return Objeto ataque
	 */
	public Ataque getB() {
		return b;
	}

	/**
	 * Retorna objeto ataque C desse pokemon, o ataque C e o terceiro ataque
	 * @return Objeto ataque
	 */
	public Ataque getC() {
		return c;
	}

	/**
	 * Retorna objeto ataque D desse pokemon, o ataque D e o quarto ataque
	 * @return Objeto ataque
	 */
	public Ataque getD() {
		return d;
		}
	/**
	 * Atualiza a vida atual do pokemon
	 * @param vida que vai ser atualizada
	 */
	public void setVida(int vida){
		this.vida=vida;
	}
	/**
	 * Retorna o ID desse pokemon
	 * @return ID desta classe pokemon
	 */
	public int getID() {
		return ID;
	}
	/**
	 * Retorna a vida deste pokemon
	 * @return vida deste pokemon
	 */
	public int getVida() {
		return vida;
	}
	/**
	 * Retorna o valor do atributo ataque deste pokemon
	 * @return atributo ataque
	 */
	public int getAtaque() {
		return ataque;
	}
	/**
	 * Retorna o valor do atributo defesa desse pokemon
	 * @return atributo defesa
	 */
	public int getDefesa() {
		return defesa;
	}
	/**
	 * Retorna o valor do atributo velocidade desse pokemon
	 * @return atributo velocidade
	 */
	public int getVelocidade() {
		return velocidade;
	}
	/**
	 * Construtor da classe pokemon, ele seta vida do pokemon sempre em 100 e de acordo com o ID do pokemon que define as caracteristicas exclusivas de
	 * cada pokemon. Como ataque,defesa,velocidade e Objetos Ataque
	 * @param ID do pokemon a ser criado
	 */
	public Pokemon(int ID){
		this.ID=ID;
		this.vida=100;
		switch(this.ID){
		//ponyta
		case 0:	
			this.ataque=37;
			this.defesa=24;
			this.velocidade=39;
			this.vantagem[0]=1;
			this.vantagem[1]=6;
			this.desvantagem[0]=3;
			this.desvantagem[1]=7;
		//shellder
		case 1:	
			this.ataque=32;
			this.defesa=49;
			this.velocidade=19;
			this.vantagem[0]=2;
			this.vantagem[1]=5;
			this.desvantagem[0]=0;
			this.desvantagem[1]=4;
		//bulbasaur
		case 2:
			this.ataque=34;
			this.defesa=34;
			this.velocidade=32;
			this.vantagem[0]=3;
			this.vantagem[1]=7;
			this.desvantagem[0]=1;
			this.desvantagem[1]=6;
		//goldeen
		case 3:	
			this.ataque=35;
			this.defesa=31;
			this.velocidade=34;
			this.vantagem[0]=0;
			this.vantagem[1]=4;
			this.desvantagem[0]=2;
			this.desvantagem[1]=8;
		//geodude
		case 4:	
			this.ataque=40;
			this.defesa=50;
			this.velocidade=10;
			this.vantagem[0]=1;
			this.vantagem[1]=8;
			this.desvantagem[0]=3;
			this.desvantagem[1]=9;
		//hoothoot
		case 5:	
			this.ataque=27;
			this.defesa=27;
			this.velocidade=46;			
			this.vantagem[0]=9;
			this.vantagem[1]=2;
			this.desvantagem[0]=1;
			this.desvantagem[1]=8;

		//caterpie
		case 6:
			this.ataque=35;
			this.defesa=45;
			this.velocidade=20;
			this.vantagem[0]=2;
			this.vantagem[1]=7;
			this.desvantagem[0]=0;
			this.desvantagem[1]=9;

		//sandshrew
		case 7:	
			this.ataque=38;
			this.defesa=43;
			this.velocidade=19;
			this.vantagem[0]=0;
			this.vantagem[1]=8;
			this.desvantagem[0]=2;
			this.desvantagem[1]=6;

		//pikachu
		case 8:	
			this.ataque=31;
			this.defesa=19;
			this.velocidade=50;
			this.vantagem[0]=3;
			this.vantagem[1]=5;
			this.desvantagem[0]=7;
			this.desvantagem[1]=4;
		//mankey
		case 9:	
			this.ataque=43;
			this.defesa=19;
			this.velocidade=38;
			this.vantagem[0]=4;
			this.vantagem[1]=6;
			this.desvantagem[0]=5;
			this.desvantagem[1]=5;

		}
	ataque();
	}
	/**
	 * Funcao que cria os objetos ataque de acordo com o ID do pokemon, ela e chamada pelo construtor somente
	 */
	protected void ataque(){
		
		switch(this.ID){

			case(2):
				//bulbasaur
			this.a = new Ataque("Corte", 20);
			this.b = new Ataque("Cabeçada",20);
			this.c = new Ataque("Folha Navalha",30);
			this.d = new Ataque("Raio Solar",30);
				break;
		
			case(6):
				//caterpie
				this.a = new Ataque ("Picada", 20);
			this.b = new Ataque ("Patada", 20);
			this.c = new Ataque ("Veneno", 30);
			this.d = new Ataque ("Queimar", 30);
				break;
				
			case (4):
				//geodude
				this.a = new Ataque ("Soco", 20);
			this.b = new Ataque ("Pedrada", 20);
			this.c = new Ataque ("Soco duplo", 30);
			this.d = new Ataque ("Abraço", 30);
				break;
			
			case(3):
				//goldeen
				this.a = new Ataque ("Mordida", 20);
			this.b = new Ataque ("Borbulha", 20);
			this.c = new Ataque ("Ataque de chifre", 30);
			this.d = new Ataque ("Jato de água", 30);
				break;
			
			case (5):
				//hoothoot
				this.a = new Ataque ("Ataque de vento",20);
			this.b = new Ataque ("Bicada",20);
			this.c = new Ataque ("Hipnose",30);
			this.d = new Ataque ("Ataque de asa",30);
				break;
				
			case(9):
				//mankey
				this.a = new Ataque ("Ataque rápido", 20);
			this.b = new Ataque ("Soco", 20);
			this.c = new Ataque ("Ataque de fúria", 30);
			this.d = new Ataque ("Chute", 30);
				break;
				
			case(8):
				//pikachu
				this.a = new Ataque ("Tapa", 20);
			this.b = new Ataque ("Ataque rápido", 20);
			this.c = new Ataque ("Raio do trovão", 30);
			this.d = new Ataque ("Relâmpago", 30);
				break;
				
			case(0):
				//ponyta
				this.a = new Ataque ("Mordida", 20);
			this.b = new Ataque ("Jato de fogo", 20);
			this.c = new Ataque ("Lança chamas", 30);
			this.d = new Ataque ("Coice", 30);
				break;
				
			case(7):
				//sandshrew
				this.a = new Ataque ("Jato de areia", 20);
			this.b = new Ataque ("Arranhar", 20);
			this.c = new Ataque ("Tempestade de areia", 30);
			this.d = new Ataque ("Seta venenosa", 30);
				break;
				
			case(1):
				//shellder
				this.a = new Ataque ("Tapa", 20);
			this.b = new Ataque ("Jato de água", 20);
			this.c = new Ataque ("Raio de gelo", 30);
			this.d = new Ataque ("Raio da aurora", 30);
				break;
		}
	}
	
}



