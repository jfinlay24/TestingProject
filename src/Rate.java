import java.math.BigDecimal;
import java.util.ArrayList;


public class Rate {

        public Rate(CarParkKind kind, BigDecimal normalRate, BigDecimal discountedRate,
                    ArrayList<Period> discountPeriods, ArrayList<Period> normalPeriods)

        {   
            CarParkKind k = kind;
            BigDecimal nr = normalRate;
            BigDecimal dr = discountedRate;
            ArrayList<Period> dp = discountPeriods;
            ArrayList<Period> np = normalPeriods;

            if(normalRate.compareTo(discountedRate)==-1){
                throw new IllegalArgumentException();
            }

            if(normalRate.compareTo(discountedRate)==-1){
                throw new IllegalArgumentException();
            }
        }

        public BigDecimal calculate(Period periodStay)
        {
            BigDecimal amount = new BigDecimal("100.5");
            return amount;
        }

}