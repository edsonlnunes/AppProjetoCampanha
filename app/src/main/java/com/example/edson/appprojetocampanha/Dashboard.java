package com.example.edson.appprojetocampanha;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Edson on 04/12/2017.
 */

public class Dashboard {
    // construtor
    public Dashboard(Context ctx) {
    }

    // Eventos

    public ArrayList<CartaoCampanha> PegaTodos() {
        ArrayList<CartaoCampanha> retorno = new ArrayList<>();

        CartaoCampanha c = new CartaoCampanha();
        c.setAfinidade(6);
        c.setTitulo("Ajudando moradores de rua");
        c.setDescricao("Venha nos ajudar a fazer a diferença. Vamos ajuda os moradores de rua.");
        c.setImagem("https://i.ytimg.com/vi/HkeyQ_BJ4Tc/maxresdefault.jpg");
        retorno.add(c);

        c = new CartaoCampanha();
        c.setAfinidade(8);
        c.setTitulo("Brincando com a criançada");
        c.setDescricao("Gosta de ajudar crianças? Ongs? Então venha participa desse evento maravilho que vai ser, vamos ajudar a ONG Canudeira de NH." +
                " Nela iremos ter várias oficinas e várias brincadeiras.");
        c.setImagem("https://s2.glbimg.com/esxq4dyQLPa3JRyOeBSTIs92gxg=/0x0:1280x960/984x0/smart/filters:strip_icc()/i.s3.glbimg.com/v1/AUTH_e84042ef78cb4708aeebdf1c68c6cbd6/internal_photos/bs/2017/P/g/lemnNFTWecNXM2u33onw/ongtexto.jpg");
        retorno.add(c);


        return retorno;
    }
}
