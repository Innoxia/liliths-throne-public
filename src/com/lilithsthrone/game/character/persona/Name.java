package com.lilithsthrone.game.character.persona;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.2.12
 * @author Innoxia
 */
public class Name {

	private static List<NameTriplet> human = (Util.newArrayListOfValues(
					new NameTriplet("Alexander", "Alex", "Alexandria"),
					new NameTriplet("Alexius", "Alex", "Alexia"),
					new NameTriplet("Alex", "Alex", "Alex"),
					new NameTriplet("Ash", "Ashe", "Ashley"),
					
					new NameTriplet("Bart", "Bailey", "Bridget"),
					new NameTriplet("Ben", "Beverly", "Bella"),
					
					new NameTriplet("Carl", "Casey", "Cadence"),
					new NameTriplet("Charlie", "Charlie", "Charlie"),
					
					new NameTriplet("Daniel", "Danny", "Dani"),
					new NameTriplet("Dale", "Devon", "Diana"),
					
					new NameTriplet("Edward", "Ed", "Elaine"),
					new NameTriplet("Eli", "Emery", "Evelyn"),
					
					new NameTriplet("Fred", "Frankie", "Felicity"),
					
					new NameTriplet("George", "Georgie", "Grace"),
					
					new NameTriplet("Harry", "Harley", "Hailey"),
					new NameTriplet("Hank", "Haiden", "Holly"),
					
					new NameTriplet("Ian", "Izzy", "Isabelle"),

					new NameTriplet("James", "Jackie", "Jasmine"),
					new NameTriplet("Jack", "Jay", "Jennifer"),
					new NameTriplet("Jean", "Jess", "Jessica"),

					new NameTriplet("Kevin", "Kel", "Katie"),
					new NameTriplet("Ken", "Kelly", "Katherine"),

					new NameTriplet("Lee", "Louie", "Lauren"),
					new NameTriplet("Leonard", "Linden", "Leah"),
					new NameTriplet("Len", "Lumi", "Laura"),
					
					new NameTriplet("Micheal", "Maddy", "Maria"),
					new NameTriplet("Mike", "Max", "Miranda"),
					
					new NameTriplet("Nicholas", "Nat", "Natalia"),
					
					new NameTriplet("Oliver", "Oli", "Olivia"),
					
					new NameTriplet("Peter", "Parker", "Paige"),
					new NameTriplet("Pete", "Phoenix", "Penelope"),
					
					new NameTriplet("Quentin", "Quinn", "Quinta"),
					
					new NameTriplet("Richard", "Robby", "Rachel"),
					
					new NameTriplet("Stanley", "Sam", "Stephanie"),
					new NameTriplet("Stan", "Sacha", "Summer"),
					
					new NameTriplet("Thomas", "Toni", "Tracy"),

					new NameTriplet("Ulysses", "Umber", "Ursula"),

					new NameTriplet("Vincent", "Val", "Violet"),
					new NameTriplet("Vinn", "Vic", "Vixen"),

					new NameTriplet("William", "Winter", "Whitney"),
					new NameTriplet("Will", "Wynne", "Willow")));
	
	private static List<NameTriplet> equine = (Util.newArrayListOfValues(
					new NameTriplet("Aqua", "Aqua", "Aqua"),
					
					new NameTriplet("Bramble", "Bramble", "Bramble"),

					new NameTriplet("Dasher", "Dasher", "Dasher"),
					new NameTriplet("Dazzle", "Dazzle", "Dazzle"),

					new NameTriplet("Flint", "Flint", "Flint"),
					
					new NameTriplet("Fleet", "Fleet", "Fleet"),
					
					new NameTriplet("Midnight", "Midnight", "Midnight"),
					new NameTriplet("Moonwind", "Moonwind", "Moonwind"),

					new NameTriplet("Nimbus", "Nimbus", "Nimbus"),

					new NameTriplet("Pearl", "Pearl", "Pearl"),
					new NameTriplet("Prixie", "Prixie", "Prixie"),
					
					new NameTriplet("Skyfeet", "Skyfeet", "Skyfeet"),
					new NameTriplet("Starr", "Starr", "Starr"),
					new NameTriplet("Spirit", "Spirit", "Spirit"),
					
					new NameTriplet("Thundermane", "Thundermane", "Thundermane"),
					new NameTriplet("Twilight", "Twilight", "Twilight"),
					
					new NameTriplet("Wildlight", "Wildlight", "Wildlight")));
	
	// Similar to equine names
	private static List<NameTriplet> reindeer = (Util.newArrayListOfValues(
			
			new NameTriplet("Dasher", "Dasher", "Dasher"),
			new NameTriplet("Dancer", "Dancer", "Dancer"),
			new NameTriplet("Prancer", "Prancer", "Prancer"),
			new NameTriplet("Vixen", "Vixen", "Vixen"),
			new NameTriplet("Comet", "Comet", "Comet"),
			new NameTriplet("Cupid", "Cupid", "Cupid"),
			new NameTriplet("Dunder", "Dunder", "Dunder"),
			new NameTriplet("Blixem", "Blixem", "Blixem"),
			
			new NameTriplet("Aqua", "Aqua", "Aqua"),
			
			new NameTriplet("Bramble", "Bramble", "Bramble"),

			new NameTriplet("Dasher", "Dasher", "Dasher"),
			new NameTriplet("Dazzle", "Dazzle", "Dazzle"),

			new NameTriplet("Flint", "Flint", "Flint"),
			
			new NameTriplet("Fleet", "Fleet", "Fleet"),
			
			new NameTriplet("Midnight", "Midnight", "Midnight"),
			new NameTriplet("Moonwind", "Moonwind", "Moonwind"),

			new NameTriplet("Nimbus", "Nimbus", "Nimbus"),

			new NameTriplet("Pearl", "Pearl", "Pearl"),
			new NameTriplet("Prixie", "Prixie", "Prixie"),
			
			new NameTriplet("Skyfeet", "Skyfeet", "Skyfeet"),
			new NameTriplet("Starr", "Starr", "Starr"),
			new NameTriplet("Spirit", "Spirit", "Spirit"),
			
			new NameTriplet("Thundermane", "Thundermane", "Thundermane"),
			new NameTriplet("Twilight", "Twilight", "Twilight"),
			
			new NameTriplet("Wildlight", "Wildlight", "Wildlight")));
	
	// No offence if your name is on here... x_x
	private static List<NameTriplet> prostitute = (Util.newArrayListOfValues(
					new NameTriplet("Amber", "Amber", "Amber"),
					new NameTriplet("Autumn", "Autumn", "Autumn"),

					new NameTriplet("Bambi", "Bambi", "Bambi"),
					new NameTriplet("Ben", "Brandy", "Brandy"),
					new NameTriplet("Brian", "Brianna", "Brianna"),

					new NameTriplet("Carl", "Chloe", "Chloe"),
					new NameTriplet("Carl", "Claudia", "Claudia"),
					new NameTriplet("Carl", "Charlene", "Charlene"),
					new NameTriplet("Chad", "Chantelle", "Chantelle"),
					new NameTriplet("Carl", "Courtney", "Courtney"),
					new NameTriplet("Carl", "Cassandra", "Cassandra"),
					new NameTriplet("Chad", "Channing", "Channing"),
					new NameTriplet("Carl", "Crystal", "Crystal"),
					new NameTriplet("Casey", "Casey", "Casey"),

					new NameTriplet("Dom", "Dolly", "Dolly"),
					new NameTriplet("Devon", "Devon", "Devon"),
					new NameTriplet("Dale", "Dakota", "Dakota"),

					new NameTriplet("Emmett", "Emmalou", "Emmalou"), // Great Scott!
					
					new NameTriplet("Harry", "Heather", "Heather"),

					new NameTriplet("Jimmy", "Jenny", "Jenny"),
					new NameTriplet("Joe", "Jolene", "Jolene"),

					new NameTriplet("Kyle", "Kylie", "Kylie"),
					new NameTriplet("Karl", "Kendra", "Kendra"),
					new NameTriplet("Karl", "Krista", "Krista"),
					new NameTriplet("Kelsey", "Kelsey", "Kelsey"),

					new NameTriplet("Lawrence", "Lauren", "Lauren"),
					
					new NameTriplet("Mike", "Misty", "Misty"),
					new NameTriplet("Mike", "Melody", "Melody"),
					new NameTriplet("Mike", "Mindy", "Mindy"),
					
					new NameTriplet("Nikki", "Nikki", "Nikki"),
					new NameTriplet("Noel", "Noel", "Noel"),
					
					new NameTriplet("Ruby", "Ruby", "Ruby"),
					new NameTriplet("Reba", "Reba", "Reba"),

					new NameTriplet("Savannah", "Savannah", "Savannah"),
					new NameTriplet("Sam", "Samantha", "Samantha"),
					new NameTriplet("Sean", "Serena", "Serena"),
					new NameTriplet("Sierra", "Sierra", "Sierra"),
					new NameTriplet("Shelby", "Shelby", "Shelby"),
					new NameTriplet("Shawn", "Shawna", "Shawna"),

					new NameTriplet("Tim", "Trina", "Trina"),
					new NameTriplet("Tammy", "Tammy", "Tammy"),
					new NameTriplet("Tom", "Tara", "Tara"),
					new NameTriplet("Taylor", "Taylor", "Taylor"),
					
					new NameTriplet("Urleen", "Urleen", "Urleen")));
	
	;
	
//	HUMAN_MALE(Race.HUMAN,
//			new String[] { "Alexander", "Al", "Alex", "Bartholomew", "Bart", "Brett", "Charles", "Carl", "Charlie", "Daniel", "Dan", "Dale", "Edward", "Ed", "Eli", "Fredrick", "Fred", "Frankie", "George", "Garry", "Gray", "Harry", "Hank", "Iago",
//					"Ian", "James", "Jack", "Jean", "Kevin", "Ken", "Kai", "Leonard", "Len", "Lee", "Micheal", "Mike", "Nicholas", "Nick", "Oliver", "Oli", "Peter", "Pete", "Quentin", "Quintin", "Richard", "Rick", "Stanley", "Stan", "Thomas", "Tom",
//					"Ulysses", "Ulfred", "Vincent", "Vinn", "William", "Will" }),
//
//	HUMAN_NEUTRAL(Race.HUMAN,
//			new String[] { "Ashley", "Alex", "Beverly", "Carol", "Danny", "Ed", "Fran", "Frankie", "Georgie", "Izzy", "Jackie", "Jay", "Jess", "Kel", "Louie", "Maddy", "Max", "Nat", "Nicky", "Oli", "Robby", "Sacha", "Sam", "Toni", "Tony", "Val",
//					"Vic" }),
//
//	HUMAN_FEMALE(Race.HUMAN,
//			new String[] { "Alice", "Alicia", "Alli", "Alex", "Amber", "Anna", "Audrey", "Bella", "Bridget", "Brooklyn", "Charlie", "Cadence", "Cathy", "Cyndi", "Dale", "Diana", "Dani", "Elaine", "Elizabeth", "Ellen", "Elli", "Emily", "Evelyn",
//					"Felicity", "Faye", "Grace", "Gia", "Hailey", "Harley", "Heidi", "Holly", "Isabelle", "Izzy", "Jackie", "Jane", "Jasmine", "Jennifer", "Jessica", "Jynx", "Kai", "Katie", "Katherine", "Katrina", "Kiara", "Kitty", "Laura", "Lauren",
//					"Leah", "Lexi", "Maria", "Mikah", "Miranda", "Missy", "Natalia", "Natasha", "Naomi", "Nicole", "Nikki", "Ola", "Olivia", "Onyx", "Paige", "Penelope", "Penny", "Quin", "Quinta", "Qi", "Raine", "Rachel", "Roxy", "Scarlett", "Stacy",
//					"Stephanie", "Summer", "Tracy", "Trixie", "Ursula", "Uma", "Val", "Violet", "Vixen", "Whitney", "Wendy", "Willow" }),
//	
//	HORSE_MORPH_MALE(Race.HORSE_MORPH,
//			new String[] { "Alex", "Colt", "Dale", "Franklin", "Jacob", "Marcus", "Sylvester" }),
//	
//	HORSE_MORPH_FEMALE(Race.HORSE_MORPH,
//			new String[] { "Alex", "Brooke", "Holly", "Moon", "Raine", "Sable", "Summer", "Vale" });
	
	private static final String[] surnames = new String[] {
			"Adams", "Ali", "Allen", "Anderson",
			"Andrews", "Armstrong", "Atkinson", "Bailey",
			"Baker", "Barker", "Barnes", "Bell",
			"Bennett", "Berry", "Booth", "Bradley",
			"Brooks", "Brown", "Butler", "Campbell",
			"Carr", "Carter", "Chambers", "Chapman",
			"Clark", "Clarke", "Cole", "Collins",
			"Cook", "Cooper", "Cox", "Cunningham",
			"Davies", "Davis", "Dawson", "Dean",
			"Dixon", "Edwards", "Ellis", "Evans",
			"Fisher", "Foster", "Fox", "Gardner",
			"George", "Gibson", "Gill", "Gordon",
			"Graham", "Grant", "Gray", "Green",
			"Griffiths", "Hall", "Hamilton", "Harper",
			"Harris", "Harrison", "Hart", "Harvey",
			"Hill", "Holmes", "Hudson", "Hughes",
			"Hunt", "Hunter", "Jackson", "James",
			"Jenkins", "Johnson", "Johnston", "Jones",
			"Kaur", "Kelly", "Kennedy", "Khan",
			"King", "Knight", "Lane", "Lawrence",
			"Lawson", "Lee", "Lewis", "Lloyd",
			"Macdonald", "Marshall", "Martin", "Mason",
			"Matthews", "Mcdonald", "Miller", "Mills",
			"Mitchell", "Moore", "Morgan", "Morris",
			"Murphy", "Murray", "Owen", "Palmer",
			"Parker", "Patel", "Pearce", "Pearson",
			"Phillips", "Poole", "Powell", "Price",
			"Reid", "Reynolds", "Richards", "Richardson",
			"Roberts", "Robertson", "Robinson", "Rogers",
			"Rose", "Ross", "Russell", "Ryan",
			"Saunders", "Scott", "Shaw", "Simpson",
			"Smith", "Spencer", "Stevens", "Stewart",
			"Stone", "Taylor", "Thomas", "Thompson",
			"Thomson", "Turner", "Walker", "Walsh",
			"Ward", "Watson", "Watts", "Webb",
			"Wells", "West", "White", "Wilkinson",
			"Williams", "Williamson", "Wilson", "Wood",
			"Wright", "Young"};
	
	private static final String[] youkoSurnames = new String[] {
			"Abiko", "Abo", "Aburaya", "Achikita",
			"Adachi", "Adachihara", "Agano", "Agata",
			"Agatsuma", "Agawa", "Aguni", "Ahane",
			"Aida", "Aihara", "Aikawa", "Aikuchi",
			"Aikyo", "Aimoto", "Ainara", "Aino",
			"Aisaka", "Aiuchi", "Akagawa", "Akagi",
			"Akahoshi", "Akai", "Akaike", "Akamatsu",
			"Akamine", "Akanishi", "Akano", "Akasaki",
			"Akashi", "Akashiro", "Akashita", "Akatsuki",
			"Akatsutsumi", "Akemi", "Aki", "Akiba",
			"Akibara", "Akimoto", "Akino", "Akisato",
			"Akishima", "Akishino", "Akita", "Akiya",
			"Amachi", "Amagai", "Amagawa", "Amai",
			"Amamiya", "Amano", "Amari", "Amatani",
			"Amaya", "Amemori", "Ametsuchi", "Amuro",
			"Amusan", "Anabuki", "Ando", "Anno",
			"Anzai", "Aoba", "Aoi", "Aoike",
			"Aoki", "Aomine", "Aonuma", "Aota",
			"Aoyagi", "Aoyama", "Aozaki", "Aozora",
			"Ara", "Aragaki", "Arai", "Arakaki",
			"Arakawa", "Araki", "Aranami", "Arashi",
			"Arashiro", "Arata", "Aratani", "Araya",
			"Arima", "Arioka", "Arisawa", "Arita",
			"Ariyoshi", "Asa", "Asahina", "Asai",
			"Asaka", "Asakawa", "Asano", "Asato",
			"Ashikaga", "Atari", "Atsuda", "Ayano",
			"Ayanokoji", "Ayanokouji", "Azahara", "Azuma",
			"Baba", "Bando", "Budou", "Bushida",
			"Chabashira", "Chage", "Chiaki", "Chiba",
			"Chibana", "Chigusa", "Chikafuji", "Chino",
			"Chisaka", "Chiura", "Chosokabe", "Chousokabe",
			"Date", "Deguchi", "Doi", "Dotani",
			"Eguchi", "Ejiri", "Enatsu", "Endo",
			"Enokida", "Enomoto", "Eto", "Etou",
			"Fuji", "Fujihara", "Fujihashi", "Fujii",
			"Fujikawa", "Fujimori", "Fujimura", "Fujinaka",
			"Fujino", "Fujinomiya", "Fujisaki", "Fujisato",
			"Fujisawa", "Fujiura", "Fujiwara", "Fujiyama",
			"Fujiyoshi", "Fukagai", "Fukami", "Fukase",
			"Fukuda", "Fukuhara", "Fukuizumi", "Fukumoto",
			"Fukunaga", "Fukushima", "Fukuyama", "Fukuyo",
			"Furukawa", "Furusawa", "Furuse", "Furuya",
			"Futaba", "Futamura", "Fuyuki", "Gato",
			"Goda", "Goto", "Gotoh", "Goya",
			"Gushiken", "Hachimitsu", "Hachimura", "Hada",
			"Haga", "Hagino", "Hagiwara", "Hajime",
			"Hama", "Hamada", "Hamadate", "Hamaguchi",
			"Hamakawa", "Hamamura", "Hamano", "Hamazaki",
			"Hanabusa", "Hanai", "Hanamura", "Hanazawa",
			"Handa", "Haneda", "Haneyama", "Hanyu",
			"Hanyuu", "Hara", "Harada", "Haraguchi",
			"Haramoto", "Harigae", "Haruki", "Haruna",
			"Haruno", "Haruta", "Haruyama", "Hasegawa",
			"Hashi", "Hashiguchi", "Hashikura", "Hashioka",
			"Hashira", "Hashitani", "Hashiyama", "Hata",
			"Hatake", "Hatano", "Hataya", "Hatsu",
			"Hattori", "Hayabusa", "Hayagawa", "Hayakawa",
			"Hayama", "Hayasaka", "Hayashibara", "Hayashida",
			"Hidaka", "Higa", "Higashi", "Higashida",
			"Higashiyama", "Himi", "Hino", "Hinode",
			"Hirai", "Hirakawa", "Hiramatsu", "Hirano",
			"Hirasaka", "Hirasawa", "Hirashima", "Hirata",
			"Hiratani", "Hiroi", "Hiromi", "Hirono",
			"Hirosawa", "Hirose", "Hiroshima", "Hirota",
			"Hiruma", "Hisamatsu", "Hitarashi", "Hitotose",
			"Hitotsuyanagi", "Hojo", "Hokinoue", "Homura",
			"Hori", "Horie", "Horigome", "Horikita",
			"Horino", "Hoshi", "Hoshimiya", "Hoshino",
			"Hoshizaki", "Hosoda", "Hosokawa", "Hosoo",
			"Houjou", "Ibuki", "Ichida", "Ichihara",
			"Ichihashi", "Ichikawa", "Ichino", "Ichinomiya",
			"Ichioka", "Ida", "Ieiri", "Iekami",
			"Igarashi", "Ige", "Ikari", "Ike",
			"Ikehara", "Ikemoto", "Ikeru", "Ikesugi",
			"Ikuta", "Imada", "Imaeda", "Imagawa",
			"Imai", "Imaishi", "Imamura", "Imari",
			"Imaruoka", "Imata", "Inagaki", "Inamura",
			"Inazuma", "Inoshishi", "Inouye", "Inui",
			"Inukai", "Iori", "Isago", "Isamu",
			"Isayama", "Ishida", "Ishido", "Ishiguro",
			"Ishihara", "Ishii", "Ishikura", "Ishimoto",
			"Ishiuchi", "Ishiwata", "Ishiyama", "Ishizuka",
			"Isobe", "Isogai", "Isozaki", "Iwaaki",
			"Iwae", "Iwamoto", "Iwano", "Iwaoka",
			"Iwasaki", "Iwashimizu", "Iwata", "Iwatani",
			"Iwayama", "Izuhara", "Izumi", "Izumo",
			"Jinnouchi", "Junko", "Kabe", "Kabuto",
			"Kadokawa", "Kadomatsu", "Kadoshima", "Kadota",
			"Kaetsu", "Kaga", "Kaiba", "Kaji",
			"Kajitani", "Kajiura", "Kajiwara", "Kaki",
			"Kakihara", "Kakimura", "Kakinuma", "Kakita",
			"Kaku", "Kakutani", "Kamata", "Kamei",
			"Kamenashi", "Kamino", "Kamiya", "Kamiyama",
			"Kamori", "Kamoto", "Kanbara", "Kanbayashi",
			"Kanbe", "Kanda", "Kaneda", "Kaneki",
			"Kaneko", "Kanemaru", "Kaneshiro", "Kanno",
			"Kanroji", "Karamatsu", "Karasu", "Kasai",
			"Kasei", "Kashiwa", "Kashiwabara", "Kashiwada",
			"Kashiwade", "Kashiwado", "Kashiwaeda", "Kashiwagi",
			"Kashiwahara", "Katagiri", "Katsura", "Kawa",
			"Kawabata", "Kawada", "Kawahara", "Kawai",
			"Kawakita", "Kawamoto", "Kawamura", "Kawanabe",
			"Kawanaka", "Kawanishi", "Kawano", "Kawasaki",
			"Kawashima", "Kawashita", "Kawata", "Kawauchi",
			"Kazama", "Kazami", "Kaze", "Kazehaya",
			"Kazetani", "Kazuyuki", "Kenma", "Kichida",
			"Kida", "Kidamura", "Kido", "Kihara",
			"Kikyo", "Kinjo", "Kino", "Kinoshita",
			"Kinugasa", "Kirigaya", "Kirimura", "Kirishima",
			"Kiriya", "Kishi", "Kishimoto", "Kitabayashi",
			"Kitagawa", "Kitahara", "Kitamura", "Kitani",
			"Kitano", "Kitao", "Kitaoka", "Kiya",
			"Kiyoko", "Kiyomizu", "Kiyota", "Kiyotake",
			"Kobashi", "Kodaira", "Kogane", "Kohira",
			"Koide", "Koigakubo", "Koike", "Kojima",
			"Kokawa", "Koki", "Komatsu", "Komatsuzaki",
			"Komiya", "Komuro", "Konaka", "Konda",
			"Kondo", "Kondou", "Konishi", "Konno",
			"Konparu", "Kosaka", "Kosugi", "Kotake",
			"Kotani", "Kotobuki", "Kotsuki", "Kouumoto",
			"Koyama", "Koyasu", "Kozue", "KÔzuke",
			"Kozuki", "Kuba", "Kubo", "Kubota",
			"Kuchiki", "Kudou", "Kuga", "Kugimiya",
			"Kuhara", "Kujira", "Kumagai", "Kumai",
			"Kumaki", "Kunida", "Kunimatsu", "Kunisaki",
			"Kurama", "Kurasawa", "Kurata", "Kuribayashi",
			"Kurihara", "Kurihashi", "Kurimoto", "Kurisu",
			"Kuriyama", "Kurizuka", "Kuroba", "Kuroda",
			"Kurogi", "Kurohashi", "Kuroiwa", "Kurokawa",
			"Kuroki", "Kuroko", "Kuromiya", "Kuronuma",
			"Kurosaka", "Kurosaki", "Kuroshima", "Kuroyanagi",
			"Kusayanagi", "Kushida", "Kushieda", "Kusunoki",
			"Kuwabara", "Kuwahara", "Kuwako", "Kyogoku",
			"Kyugoku", "Machi", "Machida", "Maebara",
			"Maejima", "Maekawa", "Maeshima", "Maeyamada",
			"Majima", "Makimura", "Makino", "Makita",
			"Manabe", "Manaka", "Mashimo", "Masuyama",
			"Matsu", "Matsubara", "Matsubayashi", "Matsuda",
			"Matsudaira", "Matsuhashi", "Matsui", "Matsukata",
			"Matsuki", "Matsumae", "Matsumura", "Matsunaga",
			"Matsunawa", "Matsuno", "Matsuo", "Matsushima",
			"Matsuura", "Matsuyama", "Matsuyuki", "Matsuzaki",
			"Mawatari", "Michizoe", "Midorikawa", "Mifune",
			"Mihara", "Mikami", "Mikazuki", "Miki",
			"Minamoto", "Minato", "Minatozaki", "Mine",
			"Misaki", "Mishima", "Miso", "Mita",
			"Mitsue", "Mitsugi", "Mitsugu", "Mitsui",
			"Miura", "Miwa", "Miya", "Miyabe",
			"Miyaguchi", "Miyahara", "Miyaichi", "Miyake",
			"Miyako", "Miyama", "Miyano", "Miyara",
			"Miyashita", "Miyata", "Miyauchi", "Miyazaki",
			"Miyazato", "Miyazawa", "Mizufuka", "Mizuhara",
			"Mizukawa", "Mizuno", "Mizusawa", "Mizuta",
			"Mizutama", "Mizutani", "Mochizuki", "Moegi",
			"Momoi", "Momose", "Moriai", "Morifuji",
			"Morihara", "Morihei", "Morikawa", "Morikita",
			"Morimoto", "Morinaka", "Morishita", "Morita",
			"Moriuchi", "Moriya", "Moriyama", "Moteki",
			"Motohashi", "Motome", "Motozawa", "Mukai",
			"Munekawa", "Mura", "Murahama", "Murahashi",
			"Murakami", "Murakawa", "Murakita", "Muramatsu",
			"Muranaka", "Muraoka", "Murashima", "Murata",
			"Muratagi", "Mushakoji", "Mushakouji", "Mushanokoji",
			"Mushanokouji", "Muto", "Mutsu", "Mutsumi",
			"Myoui", "Naegi", "Nagai", "Nagamatsu",
			"Nagano", "Nagao", "Nagaoka", "Nagashima",
			"Nagasu", "Nagato", "Nagatsuka", "Nagayama",
			"Naito", "Nakada", "Nakafuji", "Nakagame",
			"Nakagawa", "Nakai", "Nakamatsu", "Nakamine",
			"Nakamoto", "Nakanishi", "Nakao", "Nakata",
			"Nakauchi", "Nakaura", "Nakayama", "Nanami",
			"Nanashima", "Nanatsuki", "Nara", "Narisawa",
			"Narita", "Naru", "Natsukawa", "Natsumi",
			"Neho", "Neji", "Niikura", "Nikaido",
			"Ninomiya", "Nishi", "Nishida", "Nishino",
			"Nishio", "Nishiyama", "Nitta", "Nobira",
			"Nobunaga", "Nojima", "Nojiri", "Nomi",
			"Nomura", "Nonaka", "Noto", "Notou",
			"Numa", "Obara", "Oda", "Ōga",
			"Ogami", "Ogasawara", "Ogawa", "Ogita",
			"Ogura", "Oguri", "Ohama", "Ohara",
			"Ohashi", "Ohayashi", "Ohka", "Ohori",
			"Ohtani", "Oka", "Okada", "Okamoto",
			"Okamura", "Okano", "Okashima", "Okawa",
			"Okazaki", "Okochi", "Okota", "Oku",
			"Okubo", "Okudaira", "Okugawa", "Okukawa",
			"Okumura", "Okuno", "Okuyama", "Omori",
			"Omoto", "Omura", "Onishi", "Ōno",
			"Ono", "Onoue", "Oogami", "Ookouchi",
			"Ootani", "Ootono", "Osaka", "Osaki",
			"Osako", "Osato", "Osawa", "Oshima",
			"Ota", "Ōtaka", "Otaki", "Ōtani",
			"Otani", "Otonari", "Otsuka", "Ōtsuki",
			"Owari", "Oyakawa", "Oyama", "Oyamada",
			"Ozaki", "Ozawa", "Ozu", "Rikimaru",
			"Rin", "Rinbayashi", "Ritsushima", "Roka",
			"Royama", "RyŪjin", "Ryuko", "Ryumine",
			"Ryuuen", "RyŪzaki", "Saeki", "Sagami",
			"Saihara", "Saionji", "Saitama", "Saka",
			"Sakabayashi", "Sakaguchi", "Sakahara", "Sakai",
			"Sakaki", "Sakamoto", "Sakane", "Sakatani",
			"Saki", "Sakimoto", "Sakiyama", "Sakuma",
			"Sakura", "Sakurai", "Sakurami", "Sakurano",
			"Sakurazaka", "Samejima", "Samon", "Sanada",
			"Sanjo", "Sanjou", "Sano", "Sasano",
			"Sasayama", "Sasori", "Satomi", "Satoya",
			"Sawa", "Sawada", "Sawashiro", "Saza",
			"Seki", "Sekiguchi", "Senju", "Senri",
			"Serizawa", "Seto", "Setou", "Setsushi",
			"Shibasaki", "Shibata", "Shibayama", "Shibutani",
			"Shibuya", "Shima", "Shimada", "Shimadzu",
			"Shimamoto", "Shimamura", "Shimaoka", "Shimazaki",
			"Shimazu", "Shime", "Shimono", "Shimooka",
			"Shimotsuki", "Shimoyama", "Shimura", "Shinden",
			"Shinkai", "Shinobu", "Shinoda", "Shinohara",
			"Shinyama", "Shio", "Shiokawa", "Shirai",
			"Shiraishi", "Shirakawa", "Shiromori", "Shishido",
			"Shishigami", "Sho", "Shoji", "Shouyu",
			"Shoyu", "Sonoda", "Sueno", "Sueoka",
			"Suga", "Sugano", "Sugawara", "Sugieda",
			"Sugihara", "Sugimori", "Sugimoto", "Sugimura",
			"Sugino", "Sugita", "Sugitani", "Sugiura",
			"Sugiyama", "Sumisu", "Sumitomo", "Sunadori",
			"Suzukaze", "Suzumura", "Suzutani", "Suzuya",
			"Tada", "Tadokoro", "Tahara", "Taira",
			"Tajiri", "Takada", "Takagi", "Takagiri",
			"Takahara", "Takahata", "Takahide", "Takai",
			"Takaishi", "Takaki", "Takakuwa", "Takamaru",
			"Takamatsu", "Takami", "Takamitsu", "Takamori",
			"Takamura", "Takanashi", "Takano", "Takao",
			"Takase", "Takasu", "Takata", "Takayama",
			"Takayanagi", "Takazato", "Takeda", "Takehara",
			"Takei", "Takemiya", "Takemizu", "Takemura",
			"Takeno", "Takeshita", "Taketatsu", "Takeuchi",
			"Taki", "Takinoue", "Tamagawa", "Tamai",
			"Tamara", "Tamaru", "Tamashiro", "Tamatsuki",
			"Tamayama", "Tamon", "Tamura", "Tanabata",
			"Tani", "Tanigawa", "Taniguchi", "Tanihara",
			"Tanikawa", "Tanimoto", "Taniyama", "Tanji",
			"Tanose", "Tanuma", "Tatewaki", "Tatsuda",
			"Tatsumi", "Tatsuoka", "Tatsushima", "Tazawa",
			"Tekazewa", "Terada", "Terasaki", "Terauchi",
			"Tezuka", "Tobe", "Toda", "Togami",
			"Tojo", "Tokufuji", "Tokugawa", "Tokui",
			"Tomatsu", "Tomiie", "Tomita", "Tomiyasu",
			"Tomoeda", "Tomosaka", "Tomura", "Torigoe",
			"Toujou", "Toya", "Toyoda", "Toyoguchi",
			"Toyota", "Tsubo", "Tsuboi", "Tsuchida",
			"Tsuchii", "Tsuchiya", "Tsuda", "Tsudzumi",
			"Tsudzuri", "Tsuge", "Tsujihara", "Tsujii",
			"Tsujimura", "Tsujita", "Tsujiura", "Tsukahara",
			"Tsukamoto", "Tsukigata", "Tsukimi", "Tsukino",
			"Tsukiyama", "Tsukiyomi", "Tsukushi", "Tsunematsu",
			"Tsunoda", "Tsunoi", "Tsuruoka", "Tsuruta",
			"Tsutena", "Tsutsumi", "Tsuzuki", "Tsuzuno",
			"Uchida", "Uchiha", "Uchiyama", "Uehara",
			"Ueki", "Uematsu", "Uemura", "Ueshita",
			"Uesugi", "Ueta", "Umajiri", "Umeda",
			"Umehara", "Umezawa", "Umon", "Uno",
			"Uramoto", "Uraoka", "Urushihara", "Utada",
			"Utsunomiya", "Uzumaki", "Wada", "Wagahara",
			"Wakabayashi", "Wakaki", "Wakamatsu", "Wakata",
			"Wakatsuchi", "Wakuni", "Wakuri", "Watabe",
			"Watanuki", "Watari", "Yabe", "Yada",
			"Yagami", "Yagi", "Yagira", "Yakumo",
			"Yama", "Yamagata", "Yamaha", "Yamahashi",
			"Yamakawa", "Yamamizu", "Yamamura", "Yamanaka",
			"Yamane", "Yamasato", "Yamatani", "Yanagi",
			"Yanagimoto", "Yanagisawa", "Yanai", "Yasui",
			"Yasuki", "Yasunishi", "Yasuraoka", "Yasuyama",
			"Yasuzato", "Yazaki", "Yoichi", "Yoichien",
			"Yoichimae", "Yokohama", "Yokomizo", "Yokomura",
			"Yokotani", "Yokote", "Yokoyama", "Yomohiro",
			"Yonaga", "Yonamine", "Yone", "Yoneda",
			"Yoneichi", "Yonezawa", "Yoshihama", "Yoshihara",
			"Yoshii", "Yoshikawa", "Yoshimoto", "Yoshimura",
			"Yoshino", "Yoshinuma", "Yoshioka", "Yoshisawa",
			"Yui", "Yukimori", "Yukitomo", "Yukiyama",
			"Yukizome", "Yumi", "Yuuma", "Zabatsu"};
	
	private static String[] lilinNames = new String[] {
			"Lovienne",
			"Lasielle",
			"Lyssieth",
			"Lianna",
			"Lilysha",
			"Lynixi",
			"Liloria"};
	

	
	public static String getRandomName(GameCharacter gc) {
		if(gc.getFemininityValue() <= Femininity.MASCULINE.getMaximumFemininity()) {
			return getRandomTriplet(gc.getRace()).getMasculine();
			
		} else if (gc.getFemininityValue() <= Femininity.ANDROGYNOUS.getMaximumFemininity()) {
			return getRandomTriplet(gc.getRace()).getAndrogynous();
			
		} else {
			return getRandomTriplet(gc.getRace()).getFeminine();
		}
	}
	
	/** Surnames of all demons and half-demons reflect their Lilin lineage.<br/>
	 * For the case of descendents of Lyssieth, a surname would be:<br/>
	 * Lyssieth<b>martusarri</b> (Lyssieth's designated heir. Only Lilaya has this surname. This needs to be manually set.)<br/>
	 * Lyssieth<b>marturabitu</b> (Eldest daughter, if not the designated heir. As most Lilin's eldest daughters are also their designated heir, this surname is very rare.)<br/>
	 * Lyssieth<b>martuilani</b> (A direct daughter of Lyssieth.)<br/>
	 * Lyssieth<b>martu</b> (Lyssieth's grand-daughters or further.)<br/>
	 * <b>Note:</b> Imps descended from Lilin (in these examples, Lyssieth) are given the surname 'Lyssiethmartu', <i>however</i>, in LT's society, it is considered a great insult against Lyssieth to ever address an imp by this.
	 *  If they are ever transformed into a demon, they may use this surname, even if the Lilin who transformed them is not Lyssieth herself. (Again, however, that would be a grave insult against Lyssieth.)
	 * @param gc
	 * @return
	 */
	private static String getDemonSurname(GameCharacter gc) {
		String surname = "";
		GameCharacter mother = gc.getMother();
		
		if(mother!=null) {
			while(mother.getMother()!=null) {
				mother = mother.getMother();
			}
			if(mother.getSubspecies()==Subspecies.LILIN
					|| mother.getSubspecies()== Subspecies.ELDER_LILIN) {
				surname = mother.getName();
				List<GameCharacter> offspring = mother.getAllCharactersOfRelationType(Relationship.PARENT);
				if(offspring.contains(gc)) {
					offspring.sort((c1, c2) -> c1.getAgeValue()-c2.getAgeValue());
					if(offspring.get(0).equals(gc)) {
						surname+="marturabitu";
					} else {
						surname+="martuilani";
					}
				} else {
					surname+="martu";
				}
			}
			
		} else {
			surname = lilinNames[Util.random.nextInt(lilinNames.length)]+"martu";
		}
		
		return surname;
	}
	
	public static String getSurname(GameCharacter gc) {
		GameCharacter mother = gc.getMother();
		if(mother!=null) {
			while(mother.getMother()!=null) {
				mother = mother.getMother();
			}
			return mother.getSurname();
		}
		
		if(gc.getSubspecies()==Subspecies.FOX_ASCENDANT
				|| gc.getSubspecies()==Subspecies.FOX_ASCENDANT_FENNEC) {
			return youkoSurnames[Util.random.nextInt(youkoSurnames.length)];
		}
		
		switch(gc.getRace()) {
			case NONE:
			case ANGEL:
			case CAT_MORPH:
			case COW_MORPH:
			case DOG_MORPH:
			case FOX_MORPH:
			case ALLIGATOR_MORPH:
			case HARPY:
			case HUMAN:
			case WOLF_MORPH:
			case SQUIRREL_MORPH:
			case SLIME:
			case BAT_MORPH:
			case RAT_MORPH:
			case RABBIT_MORPH:
				break;
			
			case DEMON:
			case ELEMENTAL:
				return getDemonSurname(gc);
			case REINDEER_MORPH:
				break;
			case HORSE_MORPH:
				break;
		}
		
		return surnames[Util.random.nextInt(surnames.length)];
	}
	
	public static NameTriplet getRandomTriplet(Race r) {
		NameTriplet name = Util.randomItemFrom(human);
		
		switch(r) {
			case NONE:
			case ANGEL:
			case CAT_MORPH:
			case COW_MORPH:
			case DOG_MORPH:
			case FOX_MORPH:
			case ALLIGATOR_MORPH:
			case HARPY:
			case HUMAN:
			case WOLF_MORPH:
			case SQUIRREL_MORPH:
			case SLIME:
			case BAT_MORPH:
			case RAT_MORPH:
			case RABBIT_MORPH:
			break;
			
			case DEMON:
			case ELEMENTAL:
				return getDemonName();
			case REINDEER_MORPH:
				name = Util.randomItemFrom(reindeer);
				break;
			case HORSE_MORPH:
				name = Util.randomItemFrom(equine);
				break;
		}
		
		return name;
	}
	
	private static NameTriplet getDemonName() {
		String[] prefixFem = new String[] {"Aella", "Bella", "Cae", "Deva", "Ella", "Fae", "Hela", "Isa", "Katha", "Loe", "Nysa", "Oella", "Rae", "Sytha", "Vixxa", "Wynna"};
		String[] prefixMas = new String[] {"Ada", "Boro", "Foro", "Helio", "Kiri", "Zara"};
		
		String[] postfixFem = new String[] {"jyx", "ryth", "ney", "nix", "sys", "trix"};
		String[] postfixMas = new String[] {"jyx", "ryth", "ney", "nix", "sys", "trix"};
		
		String femName = prefixFem[Util.random.nextInt(prefixFem.length)] + postfixFem[Util.random.nextInt(postfixFem.length)];
		char startingChar = femName.charAt(0);

		String masName = prefixMas[Util.random.nextInt(prefixMas.length)] + postfixMas[Util.random.nextInt(postfixMas.length)];
		
		List<String> masculineNames = new ArrayList<>();
		for(String s : prefixMas) {
			if(s.charAt(0) == startingChar) {
				masculineNames.add(s);
			}
		}
		if(!masculineNames.isEmpty()) {
			masName = masculineNames.get(Util.random.nextInt(masculineNames.size())) + postfixMas[Util.random.nextInt(postfixMas.length)];
		}
		
		return new NameTriplet(masName, femName, femName);
	}
	
	public static NameTriplet getRandomProstituteTriplet() {
		return Util.randomItemFrom(prostitute);
	}
}
