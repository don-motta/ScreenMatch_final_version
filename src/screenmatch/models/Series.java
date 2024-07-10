package screenmatch.models;

import java.util.List;

public class Series extends Title{
    private String totalSeasons;

    public Series(Omdb searchResult, List<String> ratings, String totalSeasons) {
        super(searchResult, ratings);
        this.totalSeasons = totalSeasons;
    }

    @Override
    public String toString(){
        return "\nNome do filme: "+title+"\nAno de lançamento: "+year+"\nClassificação: "+rated+"\nGênero: "+genre+
                "\nDuração: "+runtime+"\nDiretor: "+director+"\nRoteirista: "+writer+"\nAtores principais: "+actors+
                "\nPaís de origem: "+country+"\nTotal de temporadas: "+totalSeasons+"\nAvaliações: ";
    }
}
