package com.azu;

import com.azu.Controls.GateControlledWithKey;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.azu.Core.*;

public class Combinator implements SoundSource{

    public Combinator(SoundSource ... sources) {
        this.sources.addAll(Arrays.asList(sources));
    }

    public List<SoundSource> sources = new ArrayList<>();

    public Combinator(List<GateControlledWithKey> list) {
        sources.addAll(list);
    }

    @Override
    public double getSoundSample() {
        double sum = sources.stream().map(e->e.getSoundSample() * 0.3d).reduce( (x, y)->x + y).orElse(0d);
        sum = sum / Core.getMaxVolume();
        return Math.tanh(sum) * Core.getMaxVolume();

    }
}
