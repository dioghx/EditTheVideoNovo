package com.video.startup.editthevideodio.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.video.startup.editthevideodio.model.Genero;

import java.util.List;

/**
 * Created by Diogo on 02/07/2017.
 */

public class MultiSpinner extends android.support.v7.widget.AppCompatSpinner implements DialogInterface.OnMultiChoiceClickListener, DialogInterface.OnCancelListener
{
//Utilizar uma lista de generos,junto a um array para salvar valores selecionados em determinadas posições,um default text
//para não retornar nulo na hora da validação,junto a um listener para ouvir as modificações da view.
    private List<String> listaGeneros;
    private boolean[] selected;
    private String defaultText;
    private MultiSpinnerListener listener;

public MultiSpinner (Context context)
{
    super(context);
}

public MultiSpinner(Context arg0, AttributeSet arg1)
{
    super(arg0, arg1);
}

public MultiSpinner (Context arg0, AttributeSet arg1,int arg2)
{
    super(arg0, arg1, arg2);
}



//Sobreescreve a função de escrever de um multiSpinner  setando valores no array baseada em suas posições da lista
    @Override
    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
            if(isChecked)
                selected[which] = true;
             else
                 selected[which] = false;
    }

    //Caso aja a deseleção ,excluir valores e retornar nulo para a array Selected
    @Override
    public void onCancel(DialogInterface dialog) {

        StringBuffer spinnerBuffer = new StringBuffer();
        boolean someUnselected = false;
        for(int i = 0;i<listaGeneros.size();i++)
        {
            if(selected[i]== true)
            {
                spinnerBuffer.append(listaGeneros.get(i));
                spinnerBuffer.append(", ");

            }
            else
            {
                someUnselected =true;
            }
        }
        String spinnerText;
        if(someUnselected) {
            spinnerText = spinnerBuffer.toString();
            if (spinnerText.length() > 2)
                spinnerText = spinnerText.substring(0, spinnerText.length() - 2);
        }
        else
        {
            spinnerText = defaultText;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item,
                new String[] { spinnerText });
        setAdapter(adapter);
        listener.onItemsSelected(selected);

    }

    @Override
    public boolean performClick()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMultiChoiceItems(listaGeneros.toArray(new CharSequence[listaGeneros.size()]),selected,this);
        builder.setPositiveButton(android.R.string.ok,
                (dialog, which) -> dialog.cancel());
        builder.setOnCancelListener(this);
        builder.show();
        return true;

    }

    public void setItems(List<String> listaGeneros, String allText,
                         MultiSpinnerListener listener)
    {
        this.listaGeneros = listaGeneros;
        this.defaultText = allText;
        this.listener = listener;

        // all selected by default
        selected = new boolean[listaGeneros.size()];
        for (int i = 0; i < selected.length; i++)
            selected[i] = true;

        // all text on the spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, new String[] { allText });
        setAdapter(adapter);
    }




    public interface MultiSpinnerListener {
        public void onItemsSelected(boolean[] selected);
    }
}
