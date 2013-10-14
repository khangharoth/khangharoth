import com.domain.Portfolio;
import com.domain.Trade;
import com.domain.TradeKey;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.valueOf;

public class DataLoader {

    public static  Map<TradeKey,Trade> loadTradeData(String fileName) throws IOException {
        Map<TradeKey,Trade> trades=new HashMap<TradeKey, Trade>();

        InputStream resourceStream= DataLoader.class.getResourceAsStream("/"+fileName);

        BufferedReader br=new BufferedReader(new InputStreamReader(resourceStream));

        String readLine=br.readLine();

        while (readLine!=null){
            String[] str=readLine.split(",");

            TradeKey key=new TradeKey(valueOf(str[0]), valueOf(str[1]));
            Trade trade=new Trade(key);
            trade.setCounterParty(str[2]);
            trade.setInstrumentType(str[3]);

            trades.put(key,trade);

            readLine=br.readLine();
        }


        return trades;
    }
    public static  Map<Integer,Portfolio> loadPortfolioData(String fileName) throws IOException {
        Map<Integer,Portfolio> portfolios=new HashMap<Integer, Portfolio>();

        InputStream resourceStream= DataLoader.class.getResourceAsStream("/"+fileName);

        BufferedReader br=new BufferedReader(new InputStreamReader(resourceStream));

        String readLine=br.readLine();

        while (readLine!=null){
            String[] str=readLine.split(",");

            Integer key= valueOf(str[0]);
            Portfolio portfolio=new Portfolio(key,str[1]);

            portfolios.put(key, portfolio);

            readLine=br.readLine();
        }


        return portfolios;
    }
}
