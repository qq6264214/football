from concurrent.futures import ThreadPoolExecutor,wait,as_completed,ALL_COMPLETED

class MyThreadPool():

    def __init__(self,max):
        self.executor = ThreadPoolExecutor(max)
        self.f_list = []

    def submit(self,fn,*args):

        future = self.executor.submit(fn,*args)
        self.f_list.append(future)

    def map(self,fn,argList):
        self.executor.map(fn,argList)


    def wait(self,return_when=ALL_COMPLETED):
        wait(self.f_list,return_when=return_when)
        print("thred task finished")

    def shutdown(self):
        self.executor.shutdown()
