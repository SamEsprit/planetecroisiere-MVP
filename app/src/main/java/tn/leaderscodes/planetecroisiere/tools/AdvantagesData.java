package tn.leaderscodes.planetecroisiere.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import tn.leaderscodes.planetecroisiere.R;
import tn.leaderscodes.planetecroisiere.remote.entities.Advantage;

public class AdvantagesData {
    static List<Advantage>  advantages = new ArrayList<Advantage>() {{
        add(new Advantage("Forfait All Inclusive offert", R.drawable.drink));
        add(new Advantage("Forfait boissons offert", R.drawable.drink));
        add(new Advantage("Tarif Basic", R.drawable.tarif));
        add(new Advantage("Pourboires offerts", R.drawable.pourboire));
        add(new Advantage("Navire à taille humaine", R.drawable.boat));
        add(new Advantage("Paysages grandioses", R.drawable.paysages));
        add(new Advantage("Navigation authentique", R.drawable.navigation));
        add(new Advantage("Expédition", R.drawable.ours));
        add(new Advantage("Croisière musicale", R.drawable.musique));
        add(new Advantage("Assistance francophone", R.drawable.drapeau));
    }};

    public static List<Advantage> getRandomAdvantage(){
        Random random = new Random();
        List<Advantage>  re_advantages= new ArrayList<>();
        for(int i=0; i < 3; i++){
            re_advantages.add(advantages.get( random.nextInt(advantages.size()) ));
        }
        return re_advantages;
    }


}
