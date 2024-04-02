package AlgLin;


public class SysTriangInf extends SysLin {
    public SysTriangInf(Matrice m, AlgLin.Vecteur v) throws IrregularSysLinException {
        super(m, v);
    }

    // Cette classe décrit un système linéaire triangulaire inférieur
    public static void main(String[] args) throws IrregularSysLinException  {
        double composants[] = new double[3];
        composants[0] = 4;
        composants[1] = 5;
        composants[2] = 3;
        AlgLin.Vecteur vecteur = new AlgLin.Vecteur(composants);
        double mat[][]= {{2, 0, 0},{3, 2, 0},{4, 2, 1}};
        Matrice matrice = new Matrice(mat);								//2x + 0y = 4
        SysLin sys = new SysTriangInf(matrice, vecteur);				//3x + 2y = 5
        System.out.println(sys.resolution());
        AlgLin.Vecteur resolution = sys.resolution();
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
        AlgLin.Vecteur resultat = new AlgLin.Vecteur(matriceSystem.nbLigne());

        double sum, coeff;

        for(int i=0;i<matriceSystem.nbLigne();i++) {
            sum=0;
            for(int j=0; j<i;j++) {
                sum+=matriceSystem.getCoef(i,j)*resultat.getCoef(j);
            }
            coeff=(secondMembre.getCoef(i)-sum)/matriceSystem.getCoef(i, i);
            resultat.remplacecoef(i, coeff);
        }
        return resultat;
    }
}