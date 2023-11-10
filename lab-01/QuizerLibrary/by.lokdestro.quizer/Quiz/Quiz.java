package Quiz;
import task_generators.*;
import tasks.Result;
import tasks.Task;

public class Quiz {
    /**
     * @param generator генератор заданий
     * @param taskCount количество заданий в тесте
     */
    public Quiz(TaskGenerator generator, int taskCount) { 
        // ...
    	gen = generator;
    	cnt = taskCount;
    	WAcnt = 0;
    	OKcnt = 0;
    	InCorrectcnt = 0;
    	giveSame = false;
    }
    
    /**
     * @return задание, повторный вызов вернет слелующее
     * @see Task
     */
    public Task nextTask() {
    	if (giveSame) {
    		giveSame = false;
    		System.out.println(curTask.getText());
    		return curTask;
    	} else {
    		curTask = gen.generate();
    		System.out.println(curTask.getText());
    		return curTask;
    	}
    }
    
    /**
     * Предоставить ответ ученика. Если результат {@link Result#INCORRECT_INPUT}, то счетчик неправильных 
     * ответов не увеличивается, а {@link #nextTask()} в следующий раз вернет тот же самый объект {@link Task}.
     */
    public Result provideAnswer(String answer) {
    	if (curTask.validate(answer) == Result.INCORRECT_INPUT) {
    		giveSame = true;
    		InCorrectcnt++;
    		return Result.INCORRECT_INPUT;
    	}
    	System.out.println(curTask.getAns());
    	if (curTask.validate(answer) == Result.WRONG) {
    		WAcnt++;
    		return Result.WRONG;
    	} else {
    		OKcnt++;
    		return Result.OK;
    	}
    }
    
    /**
     * @return завершен ли тест
     */
    public boolean isFinished() {
        return OKcnt + WAcnt == cnt;
    }
    
    /**
     * @return количество правильных ответов
     */
    int getCorrectAnswerNumber() {
        return OKcnt;
    }
    
    /**
     * @return количество неправильных ответов
     */
    int getWrongAnswerNumber() {
        return WAcnt;
    }
    
    /**
     * @return количество раз, когда был предоставлен неправильный ввод
     */
    int getIncorrectInputNumber() {
        return InCorrectcnt;
    }
    
    /**
     * @return оценка, которая является отношением количества правильных ответов к количеству всех вопросов. 
     *         Оценка выставляется только в конце!
     */
    public double getMark() {
        // ...
    	if (isFinished()) {
    		return OKcnt / cnt;
    	} else {
    		return -1;
    	}
    }
    
    boolean giveSame;
    Task curTask;
    int WAcnt;
    int OKcnt;
    int InCorrectcnt;
    TaskGenerator gen;
    int cnt;
}