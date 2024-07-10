package screenmatch.models;

import java.util.List;

public class Movie extends Title{

    public Movie(Omdb searchResult, List<String> ratings) {
        super(searchResult, ratings);
    }

    @Override
    public String toString(){
        return "\nNome do filme: "+title+"\nAno de lançamento: "+year+"\nClassificação: "+rated+"\nGênero: "+genre+
                "\nDuração: "+runtime+"\nDiretor: "+director+"\nRoteirista: "+writer+"\nAtores principais: "+actors+
                "\nPaís de origem: "+country+"\nAvaliações: ";
    }
}
