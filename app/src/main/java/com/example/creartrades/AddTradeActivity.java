package com.example.creartrades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;


import com.example.creartrades.config.Constantes;
import com.example.creartrades.databinding.ActivityAddTradeBinding;
import com.example.creartrades.modelos.Trade;

public class AddTradeActivity extends AppCompatActivity {

    private ActivityAddTradeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddTradeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        inicializarSpinners();

        binding.btnCancelarAddTradeActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        binding.btnCrearAddTradeActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Trade trade;

                if ((trade = crearTrade()) != null){
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Constantes.TRADE, trade);
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

    }

    private Trade crearTrade() {
        if (binding.spEstadoAddTradeActivity.getSelectedItem() != " " ||
            binding.spEntradaAddTradeActivity.getSelectedItem() != "" ||
            binding.spAnyoAddTradeActivity.getSelectedItem() != "" ||
            binding.spDiaAddTradeActivity.getSelectedItem() != "" ||
            binding.spMesAddTradeActivity.getSelectedItem() != "" ||
            binding.spMercadoAddTradeActivity.getSelectedItem() != "" ||
            !binding.txtContratosAddTradeActivity.getText().toString().isEmpty() ||
            !binding.txtValorPuntoAddTradeActivity.getText().toString().isEmpty() ||
            !binding.txtPuntosAddTradeActivity.getText().toString().isEmpty() ||
            binding.spEmocionAddTradeActivity.getSelectedItem() != ""){

            String estado = binding.spEstadoAddTradeActivity.getSelectedItem().toString();
            String entrada = binding.spEntradaAddTradeActivity.getSelectedItem().toString();
            int anyo = Integer.parseInt(binding.spAnyoAddTradeActivity.getSelectedItem().toString());
            int dia = Integer.parseInt(binding.spDiaAddTradeActivity.getSelectedItem().toString());
            String mes = binding.spMesAddTradeActivity.getSelectedItem().toString();
            String mercado = binding.spMercadoAddTradeActivity.getSelectedItem().toString();
            int contratos = Integer.parseInt(binding.txtContratosAddTradeActivity.getText().toString());
            float valorPunto = Float.parseFloat(binding.txtValorPuntoAddTradeActivity.getText().toString());
            float puntos = Float.parseFloat(binding.txtPuntosAddTradeActivity.getText().toString());
            String emocion = binding.spEmocionAddTradeActivity.getSelectedItem().toString();

            return new Trade(estado, entrada, anyo, dia, mes, mercado, contratos, valorPunto, puntos, emocion);
        }
        Toast.makeText(this, "No se puede crear el trade", Toast.LENGTH_SHORT).show();
        return null;
    }

    private void inicializarSpinners() {
        ArrayAdapter<CharSequence> adapterEstado = ArrayAdapter.createFromResource(this, R.array.elegirEstado, android.R.layout.simple_spinner_item);
        binding.spEstadoAddTradeActivity.setAdapter(adapterEstado);

        ArrayAdapter<CharSequence> adapterEntrada = ArrayAdapter.createFromResource(this, R.array.elegirEntrada, android.R.layout.simple_spinner_item);
        binding.spEntradaAddTradeActivity.setAdapter(adapterEntrada);

        ArrayAdapter<CharSequence> adapterAnyo = ArrayAdapter.createFromResource(this, R.array.elegirAnyo, android.R.layout.simple_spinner_item);
        binding.spAnyoAddTradeActivity.setAdapter(adapterAnyo);

        ArrayAdapter<CharSequence> adapterDia = ArrayAdapter.createFromResource(this, R.array.elegirDia, android.R.layout.simple_spinner_item);
        binding.spDiaAddTradeActivity.setAdapter(adapterDia);

        ArrayAdapter<CharSequence> adapterMes = ArrayAdapter.createFromResource(this, R.array.elegirMes, android.R.layout.simple_spinner_item);
        binding.spMesAddTradeActivity.setAdapter(adapterMes);

        ArrayAdapter<CharSequence> adapterMercado = ArrayAdapter.createFromResource(this, R.array.elegirMercado, android.R.layout.simple_spinner_item);
        binding.spMercadoAddTradeActivity.setAdapter(adapterMercado);

        ArrayAdapter<CharSequence> adapterEmocion = ArrayAdapter.createFromResource(this, R.array.elegirEmocion, android.R.layout.simple_spinner_item);
        binding.spEmocionAddTradeActivity.setAdapter(adapterEmocion);
    }
}