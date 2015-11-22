package com.mycompany.progseminar.tetris.optimizer;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.mycompany.progseminar.tetris.ai.AI;
import com.mycompany.progseminar.tetris.ai.AiParametrization;
import com.mycompany.progseminar.tetris.core.SimpleTetris;
import com.mycompany.progseminar.tetris.core.Tetris;
import static com.mycompany.progseminar.tetris.optimizer.Helpers.range;
import java.util.*;
import static java.util.Arrays.asList;
import java.util.function.Function;
import static java.util.stream.Collectors.toList;

public class Optimizer {

    private static final String X_REG = "\\bX\\b";
    private static final String P_REG = "\\bP\\b";

    private static final Input IN_1 = new Input("in 1", "IIOZOTISJJLTISSOSLSIZJLOSSLOSOZILIOIOSLSLTZJJOIIILJLLZTZOITSLOZISIOZTLLLJJJOZIOLJJOZSITLTLOZTZSIJSJJZILOLJIZLLSZSSTSLTOSSOSOJIJLZITIZJZOZJLTSIOOSOLSLSSSOOZJSIIOZLTZJTLIJLJLJSOTSOILTSSZSISOTSOTTZZTTJZLOZLTOLZTJSOSLSILLISSZTSZLZLZIZJTJZTZLLOIIJIIIOTTIIIZZLJISLJISOOLOOOJSJOIZILLTTZZTTIOLZTJJOOTOILSOLJJSILIOJITTJJSOIITLSZOIZTSJOOZJSIZOSSSZJTTJSTSJIJSOLTTTIOOTTOTLILOIJSLZLOOSTTTSLOOTOZTSZLTSTIOZSSJTIZJLLOIOISSTTSTSOTTZJJSZJIOIIOZITLTITIISTOTTJSOLLOJJSTSLJJZSSJSLOOTOOOJITZOJLLITIJSSJSTZOOTOIZZZITOLSOO");
    private static final Input IN_2 = new Input("in 2", "STZTOOZSSTLISLSIIZIJOJOJTIJZIJIZIILTJSIOSTTJSILTSZSLTOSOSILLJLSSOLJSILTITOITZJTITZITISTZOIOISJSJSIOOOZOZJZSTZTOZZTILTLJLLILSIJJZLLTOJTZOIIJIZSJOZLLIZOZZTOOITIJSZOJLTIOOJZZJZSTIOOTIOSJIOJSZSSSJIITSJJIJZOTZZISLOTISTOLOTSLLOSISZZTTLIJZIOLJZTTLOILZISZSTTLJLTLOZSITZTZOSLOSTJJZLJSLSSSIJJZOLIJLJOJZTOSLZIJSSSTZJSILSZSJTTSIZOZSJTOZSJTLITSSTLTOOOSOLJOJTOLLIILOOILTTSIJIOJLITOIOLTLJZITLZZTLJLIILOTOSLZJZIJJOSSLJZOJZOZIJZSOSJZSSTTTZILSJOOOSOZTSZOSZTOITJJTTLJZLJZJSZOZJZOZLIIJOZJLZZSIIOZSIIJOOIOTSJJSOZZZTIOTTJI");
    private static final Input IN_3 = new Input("in 3", "JLJLISTIZOISZJZSTIJSTOJOLSIIOSOOOZZZTTLZOLJLIZIOJZLIJTSJIISOSISJZSOLSTOJOSJOSTSZSJITTITIZLJZTOJLOOLITIZLOIJZJOJLITZLSISLSLOLSTOLSOOTJSILOIOSSLSIZLJLJTLIOZJJSTJLTTLJLILSLZOLTZTJLIJTZLZJOOLLJOLTLOOTOZLILJLZITSOTOTLZLTSOLTSOOTSOLJOISJSZLZLZTIISJOJJLTTIIZZZJZIZLZIZSJTJOTOSJIJIZSJJZIJLTLJOOSTOTSTILJSJOTJOTIZOTZTSOSJLITJTZTOIOIOLJTOLIIOOZOJZIOTISOOZZOJTLZJTIOJOSTLSOLOITIJTISTZOZTLTLZLOIOZILOIOOTLOOLIZTJZSTLILJILSSJZLTILITIIOTOSSJZISOJZJSZOTIISSZSISLSOIOOIJTOOTOZIZLSOZZLIZLJTZIJTOJITJITSLLOTIOSOZLJZOIS");
    private static final Input IN_4 = new Input("in 4", "IILTSOILZLJOLJITIZSJOJTZZLSSTLOSILJOITOLLIITLLLLOSJZTOTSZTJLLZTLJZZLLOOJTJSTJOJLTOIJLTZITLOOOOLZJSLLSJITOJOOJOLZZIJZZZLTSOOSOIOIITZSSOJJTTTLSOTILSJJITSIZZIOLJIOLLSSLTSSZIIIZLJSOZOJJZJTOSOTSISOLSSZOSSILJJTIZIZLILOZLLIOZTZIZLTOSJJIZSZLLJLOIIOLIILJSSZJSTZOIJLTSSSOLIJLSSJSLJITIIOLZOLTZLTLSJLTZJZJSITJZLITSSZLTTOZLJOJTJJIZTJZZZJLTTLOSZZITTTZTZZLLZTLIIIIJZOLZTJTIOIISLOSITSIJSJISIIOTLZLZILOIIJZOLLJTZTZSIZSLLLOSIOTSJOJTLSSLLJOSSZISJJTSLZTLSZTOIOTOSJLZSIJTZILIJTZIZSITSSIIZILLOZLZJTSIZOSZTJOSLLOSJOSJTIOTJS");
    private static final Input IN_5 = new Input("in 5", "LILLZJTLJLOIILOJSJOOSJJTIJSLILJOTSTLOLJSSTLOLZOJTLSLSSSZZSZOSLOTLZZOOOLISSIJJLIJIIZZSJTTIJOZZJSOSJZLTOTJLLOOLITOJLZIOZZTZOLLISTOLJTSIJOZILOOSIOOZJOLJLTJTTSSOSSSZSSISLITSTTZOSLZLOIOOJLLJOTSSZSSZJLZLTOZISZTTOTZISTJOZJSISOOLZZTITLZZLTOTLTTITOOJIZTOJLIZJTJZTSJJTOIIITJLLTIOJOLJSOZSOZSISLJTILOZIJLTLZJZSOOSLLZTJJJZOIJZJLIILIIOIIZJIZIOZLZZTTLSLSZZJIIJJTJSIJJOZZTZLOLSLOOJZLTOZLOJJZLJITIOSITZZSOZILOJTZJZOZZIIZZLOSIOIOLZSLLIIILZSJOIZSSTJZSSOSJOSJZJILZITTLSOITZISZZZITZTLLZIJIOLLLLJOLOLSLOZSJSSISLLLJIOJOSZII");
    private static final List<Input> ALL_INPUTS = asList(IN_1, IN_2, IN_3, IN_4, IN_5);

    private static DoubleEvaluator eval = new DoubleEvaluator();

    public static void main(String[] args) throws Exception {
        List<Function<Integer, Integer>> heightFuns = fromFunction(
            "P * X",
            asList(range(1.0, 10.0, 1.0)));
        List<Function<Integer, Integer>> flatnessFuns = fromFunction(
            "P * X",
            asList(range(1.0, 10.0, 1.0)));
        List<Function<Integer, Integer>> bubbleFuns = fromFunction(
            "P * X",
            asList(range(1.0, 10.0, 1.0)));
        List<Function<Integer, Integer>> rowFuns = fromFunction(
            "P * X",
            asList(range(1.0, 10.0, 1.0)));
        List<Run> allRuns = new ArrayList<>();
        int at = 0;
        for (AiParametrization params : doPermutations(heightFuns, flatnessFuns, bubbleFuns, rowFuns)) {
            if (at % 100 == 0) {
                System.out.println("at " + at);
            }
            if (at % 1000 == 0) {
                allRuns = filter(allRuns, 100);
            }
            List<Integer> results = ALL_INPUTS.stream()
                .map(in -> run(params, in))
                .collect(toList());
            allRuns.add(new Run(params, results));
            at++;
        }
        System.out.println(allRuns.get(allRuns.size() - 1));
        System.out.println(allRuns.get(0));
    }

    private static List<Run> filter(List<Run> current, int keep) {
        if (current.size() < 2 * keep) {
            return current;
        }
        Collections.sort(current);
        List<Run> retained = new ArrayList(keep * 2);
        System.out.println("bad from " + current.get(0) + " to " + current.get(keep));
        System.out.println("good from " + current.get(current.size() - keep) + " to " + current.get(current.size() - 1));
        retained.addAll(current.subList(0, keep));
        retained.addAll(current.subList(current.size() - keep, current.size()));
        return retained;
    }

    public static int run(AiParametrization params, Input in) {
        Tetris t = newTetris(in);
        AI ai = new AI(t, params);
        int c = 0;
        while (!t.isOver()) {
            ai.round();
            c++;
        }
        return c;
    }

    private static Tetris newTetris(Input in) {
        return new SimpleTetris(10, 20, in.infinite());
    }

    public static List<AiParametrization> doPermutations(List<Function<Integer, Integer>> height,
                                                         List<Function<Integer, Integer>> flatness,
                                                         List<Function<Integer, Integer>> bubble,
                                                         List<Function<Integer, Integer>> row) {
        List<AiParametrization> parametrizations = new ArrayList<>();
        for (List<Function<Integer, Integer>> parametrization : permutations(asList(height, flatness, bubble, row))) {
            parametrizations.add(new AiParametrization(
                parametrization.get(0),
                parametrization.get(1),
                parametrization.get(2),
                parametrization.get(3)
            ));
        }
        return parametrizations;
    }

    public static List<Function<Integer, Integer>> fromFunction(final String formula,
                                                                final List<List<Double>> parameterSuppliers) {
        List<Function<Integer, Integer>> funs = new ArrayList<>();
        for (List<Double> instance : permutations(parameterSuppliers)) {
            funs.add(fromFunctionOne(formula, instance));
        }
        return funs;
    }

    public static Function<Integer, Integer> fromFunctionOne(final String formula,
                                                             final List<Double> parameters) {
        String formulaFilled = formula;
        for (Double para : parameters) {
            formulaFilled = formulaFilled.replaceFirst(P_REG, String.valueOf(para));
        }
        final String f = formulaFilled;
        return new Function<Integer, Integer>() {

            @Override
            public Integer apply(Integer x) {
                return eval.evaluate(f.replaceFirst(X_REG, String.valueOf(x))).intValue();
            }

            @Override
            public String toString() {
                return "fun " + formula + " para " + parameters;
            }
        };
    }

    public static <T> List<List<T>> permutations(List<List<T>> of) {
        List<Counter> counters = new ArrayList<>();
        for (List<T> poss : of) {
            counters.add(new Counter(poss.size()));
        }
        List<List<T>> permutations = new ArrayList<>();
        while (!counters.get(0).hasCycled()) {
            Iterator<List<T>> poss = of.iterator();
            permutations.add(counters.stream().map(c -> poss.next().get(c.at())).collect(toList()));
            int at = counters.size() - 1;
            while (true) {
                boolean willCycle = counters.get(at).willCycle();
                counters.get(at).turn();
                at--;
                if (!willCycle || counters.get(0).hasCycled()) {
                    break;
                }
            }
        }
        return permutations;
    }

    private static class Counter {

        private int limit;
        private int at;
        private boolean hasCycled;

        public Counter(int limit) {
            this.limit = limit;
            this.at = 0;
        }

        public boolean willCycle() {
            return this.at == this.limit - 1;
        }

        public boolean hasCycled() {
            return this.hasCycled;
        }

        public int at() {
            return this.at;
        }

        public void turn() {
            this.at++;
            if (this.at == this.limit) {
                this.at = 0;
                this.hasCycled = true;
            }
        }
    }
}
