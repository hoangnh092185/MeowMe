package n8.meowme.util;

import java.util.ArrayList;

import n8.meowme.models.Petfinder;

/**
 * Created by Guest on 12/16/16.
 */
public interface OnPetfinderSelectedListener {
    public void onPetfinderSelected(Integer position, ArrayList<Petfinder> petfinders, String source);
}
