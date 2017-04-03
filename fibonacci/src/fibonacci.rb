class Class
  def subclasses
    ObjectSpace.each_object(Class).select { |klass| klass < self }
  end

end


class Fibonacci_calculator
  define_singleton_method(:calculator_for) do
  |a_number|
    self.subclasses.detect(lambda {raise 'invalid fibonacci position'}){ |subclass| subclass.can_calculate_fibonacci_for(a_number)}
  end
end
class Fixnum
  def fibonacci

    Fibonacci_calculator.calculator_for(self).new.fibonacci_of(self)

  end
end
class Fibonacci_calculator_for_base_position<Fibonacci_calculator
  define_singleton_method(:can_calculate_fibonacci_for) do
  |a_number|
    a_number==1 || a_number==0

  end
  def fibonacci_of (aNumber)
    1
  end

end

class Fibonacci_calculator_for_no_base_position<Fibonacci_calculator
  define_singleton_method(:can_calculate_fibonacci_for) do
  |a_number|
    a_number>1

  end
  def fibonacci_of(aNumber)
   (aNumber-1).fibonacci+(aNumber-2).fibonacci
  end
end

