import schedule
import time
import database as DB
from predictResult import firstAna,linchangAna
from updateForecastData import updateMatchRealTime,updateMacthLinchang
import traceback
class Task():
    def __init__(self):
        self.database = DB.Database('localhost', 'root', 'root', 'sports')
    def job(self):
        print("start")
        try:
            updateMatchRealTime(self.database)
            print('updateMatchRealTime success')
        except Exception as e:
            traceback.print_exc()
        try:
            updateMacthLinchang(self.database)
            print('updateMacthLinchang success')
        except Exception as e:
            traceback.print_exc()
        try:
            firstAna(self.database)
            print('firstAna success')
        except Exception as e:
            traceback.print_exc()
        try:
            linchangAna(self.database)
            print('linchangAna success')
        except Exception as e:
            traceback.print_exc()
    def run(self):
        schedule.every(5).minutes.do(self.job)
        while True:
            schedule.run_pending()
            time.sleep(1)

if __name__ == '__main__':
    task = Task()
    task.run()


