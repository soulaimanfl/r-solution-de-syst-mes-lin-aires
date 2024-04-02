package AlgLin;


public abstract class SysLin {

    private int ordre;							//nb de lignes/colonnes de la matrice ou second membre
    protected Matrice matriceSystem;			//matrice du syst_me
    protected AlgLin.Vecteur secondMembre;				//second membre du système


    SysLin(Matrice m, AlgLin.Vecteur secondMembre) throws IrregularSysLinException{
        if(m.nbLigne() == m.nbColonne()) {		//m est carrée et même taille secondM
            this.ordre = m.nbLigne();
            this.secondMembre = secondMembre;
            this.matriceSystem = m;
        } else {
            throw new IrregularSysLinException("Matrice non carrée ou mauvais second membre");
        }
    }

    public int getOrdre() {
        return ordre;
    }

    public Matrice getMatriceSystem() {
        return matriceSystem;
    }

    public AlgLin.Vecteur getsecondMembre() {
        return secondMembre;
    }

    public abstract AlgLin.Vecteur resolution() throws IrregularSysLinException ;
}