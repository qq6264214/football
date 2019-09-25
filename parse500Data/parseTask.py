import schedule
import time
import database as DB
from parse500 import updateTask
import traceback

class Task():
    def __init__(self):
        self.database = DB.Database('localhost', 'root', 'root', 'sports')
    def job(self):
        print("start")
        try:
            updateTask(self.database)
            print('parse 500 success')
        except Exception as e:
            traceback.print_exc()
        try:
            DB.updateForecastDataResult(self.database)
            print('update forecast result success')
        except Exception as e:
            traceback.print_exc()

    def run(self):
        schedule.every(12).hours.do(self.job)
        while True:
            schedule.run_pending()
            time.sleep(1)

if __name__ == '__main__':
    task = Task()
    task.run()