import screenmatch.models.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class ScreenMatch {
    public static void main (String [] args) throws IOException, InterruptedException {
        String search;
        char exit;
        Scanner sc = new Scanner(System.in);
        FileWriter logSearch = new FileWriter("LogSearch.txt");
        System.out.println("Olá! Seja bem vindo ao ScreenMatch, seu database de filmes e séries.");
        do {
            System.out.println("Qual o nome do filme ou série deseja pesquisar?");
            search = sc.nextLine().replace(' ', '+');
            JsonFilter searchResult = new JsonFilter(search);
            if (searchResult.getResponse()) {
                if (searchResult.getType().equalsIgnoreCase("movie")) {
                    Movie result = new Movie(searchResult.getSearchResult(), searchResult.getRatings());
                    System.out.println(result+"\n"+result.printRatings());
                    logSearch.write("****************************************************************\n"+result+"\n"+result.printRatings()+"\n");
                } else {
                    Series result = new Series(searchResult.getSearchResult(), searchResult.getRatings(), searchResult.getTotalSeasons());
                    String ratings = result.printRatings();
                    System.out.println(result+"\n"+result.printRatings());
                    logSearch.write("****************************************************************\n"+result+"\n"+result.printRatings()+"\n");
                }
            } else {
                System.out.println("Desculpe! Serie ou filme não encontrado!");
            }
            exit = ' ';
            while (exit != 's' && exit != 'n') {
                System.out.println("Deseja fazer uma nova pesquisa? s/n");
                exit = sc.nextLine().charAt(0);
            }
        } while (exit == 's');
        System.out.println("Programa finalizado com sucesso. Volte sempre!");
        logSearch.close();
    }
}
