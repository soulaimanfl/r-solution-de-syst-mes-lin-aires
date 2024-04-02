package AlgLin;


public class SysTriangSup extends SysLin{
    SysTriangSup(Matrice m, AlgLin.Vecteur secondMembre) throws IrregularSysLinException  {
        super(m, secondMembre);
    }

    // Cette classe décrit un système linéaire triangulaire supérieur.
    public static void main(String[] args) throws IrregularSysLinException {
        double composants[] = new double[3];
        composants[0] = 4;
        composants[1] = 5;
        AlgLin.Vecteur vecteur = new AlgLin.Vecteur(composants);
        double mat[][]= {{2,1},{0,2}};
        Matrice matrice = new Matrice(mat);								//2x + 1y = 4
        SysLin sys = new SysTriangSup(matrice, vecteur);				//0x + 2y = 5
        System.out.println(sys.resolution());
        AlgLin.Vecteur resolution = sys.resolution();

        Matrice resolu = new Matrice(resolution.getTaille(), 1);

        Matrice.verif_produit(matrice, resolu);
        Matrice resultat = Matrice.produit(matrice,resolu);
        Matrice.verif_addition(resultat, resolu);
        Matrice resulatFinal = Matrice.addition(resultat, resolu);
        AlgLin.Vecteur norme = new AlgLin.Vecteur(resulatFinal.nbLigne());
        for(int i = 0; i < resulatFinal.nbLigne(); i++){
            norme.remplacecoef(i, resulatFinal.getCoef(i,0));
        }
        double normeFinal =	AlgLin.Vecteur.normeL1(norme);
        if(normeFinal <= 0.0 || normeFinal > Matrice.EPSILON){
            System.out.println("La norme du vecteur est null ou plus exactement très petite");
        }
        double normeFinalL2 =	AlgLin.Vecteur.normeL2(norme);
        if(normeFinalL2 <= 0.0 || normeFinalL2 > Matrice.EPSILON){
            System.out.println("La norme du vecteur est null ou plus exactement très petite");
        }
        double normeFinalLi =	AlgLin.Vecteur.normeInfini(norme);
        if(normeFinalLi <= 0.0 || normeFinalLi > Matrice.EPSILON){
            System.out.println("La norme du vecteur est null ou plus exactement très petite");
        }
    }

    public AlgLin.Vecteur resolution() throws IrregularSysLinException {
        AlgLin.Vecteur resultat = new AlgLin.Vecteur(getMatriceSystem().nbLigne());
        double somme, coefficient;
        for (int i = matriceSystem.nbLigne()-1; i >= 0; i--) {
            somme = 0;
            for (int j = matriceSystem.nbLigne()-1; j > i; j--){
                somme += getMatriceSystem().getCoef(i, j) * resultat.getCoef(j);
            }
            coefficient = (secondMembre.getCoef(i) - somme) / matriceSystem.getCoef(i, i);
            resultat.remplacecoef(i, coefficient);
        }
        return resultat;
    }
}