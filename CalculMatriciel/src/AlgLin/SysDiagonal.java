package AlgLin;


public class SysDiagonal extends SysLin {

    public SysDiagonal(Matrice m, AlgLin.Vecteur v) throws IrregularSysLinException  {
        super(m, v);
    }

    // Cette classe décrit un système linéaire diagonal
    public static void main(String[] args) throws IrregularSysLinException {
        double composants[] = new double[3];
        composants[0] = 2;
        composants[1] = 1;
        composants[2] = -13;
        AlgLin.Vecteur vecteur = new AlgLin.Vecteur(composants);
        double mat[][]= {{1,0, 0},{0,1, 0},{0,0,1}};
        Matrice matrice = new Matrice(mat);								//2x + 0y = 4
        SysLin sys = new SysDiagonal(matrice, vecteur);					//0x + 3y = 5

        AlgLin.Vecteur resolution = sys.resolution();
        System.out.println(resolution);
        Matrice resolu = new Matrice(resolution.getTaille(), 1);
        for(int i = 0; i < resolu.nbLigne(); i++){
            resolu.remplacecoef(i, 0, resolution.getCoef(i));
        }
        Matrice v = new Matrice(vecteur.getTaille(), 1);
        for(int i = 0; i < vecteur.getTaille(); i++){
            v.remplacecoef(i, 0, vecteur.getCoef(i));
            v.remplacecoef(i, 0, - v.getCoef(i, 0));
        }

        Matrice.verif_produit(matrice, resolu);
        Matrice resultat = Matrice.produit(matrice,resolu);
        Matrice.verif_addition(resultat, v);
        Matrice resulatFinal = Matrice.addition(resultat, v);
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
        Matrice matrice = getMatriceSystem();

        AlgLin.Vecteur res = new AlgLin.Vecteur(matrice.nbLigne());

        for(int i = 0; i < matrice.nbLigne(); i++) {
            res.remplacecoef(i, secondMembre.getCoef(i) / matrice.getCoef(i, i));
        }
        return res;
    }
}