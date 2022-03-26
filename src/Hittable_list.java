import java.util.ArrayList;

public class Hittable_list implements Hittable{
    private final ArrayList<Hittable> objects;

    public Hittable_list() {
        this.objects = new ArrayList<>();
    }

    public void clear() {
        this.objects.clear();
    }

    public void add(Hittable object) {
        this.objects.add(object);
    }

    @Override
    public boolean hit(Ray r, double t_min, double t_max, Hit_record rec) {
        Hit_record temp_rec = new Hit_record();
        boolean hit_anything = false;
        double closest_so_far = t_max;

        for (Hittable object : this.objects) {
            if (object.hit(r, t_min, closest_so_far, temp_rec)) {
                hit_anything = true;
                closest_so_far = temp_rec.getT();
                rec.clone(temp_rec);
            }
        }

        return hit_anything;
    }
}