package AlgLin;


public class IrregularSysLinException extends Exception
{
    private final String message;

    public IrregularSysLinException()
    {
        this.message = null;
    }

    public IrregularSysLinException(String message)
    {
        this.message = message;
    }

    @Override
    public String toString()
    {
        if( this.message == null || this.message.isEmpty() ) return "Le système est irrégulier !";

        return this.message;
    }
}