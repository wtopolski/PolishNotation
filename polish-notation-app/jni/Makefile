CXX=g++
LFLAGS=
OBJS=token.o operand.o operator.o support.o

all: clean app

app: $(OBJS)
	$(CXX) main.cpp $^ -o $@

$(OBJS): %.o : %.cpp
	$(CXX) -c $< -o $@

clean:
	rm -f *.o app

.PHONY: all clean
