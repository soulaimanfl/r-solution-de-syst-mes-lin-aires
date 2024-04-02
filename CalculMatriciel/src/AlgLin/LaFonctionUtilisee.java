package AlgLin;


public class LaFonctionUtilisee implements  FonctionGenerale {
    private int calc = 0;

    public LaFonctionUtilisee(int fonc) {
        calc = fonc;
    }

    @Override
    public double evaluationDeLaFonction(Matrice m) {
        if(calc == 0) {
            return m.norme_1();
        } else {
            return m.norme_inf();
        }
    }
}
