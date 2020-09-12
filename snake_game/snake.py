class Snake:

    def __init__(self, map_size):
        self.length = 1
        self.direction = 'up'
        self.map_size = map_size
        self.position = [(map_size//2, map_size//2)]

    def is_alive(self):
        head = self.position[-1]
        if (head[0] >= self.map_size) or (head[0] <= 0):
            return False
        if (head[1] >= self.map_size) or (head[1] <= 0):
            return False
        for tail in self.position[:-1]:
            if head == tail:
                return False
        return True

    def get_head(self):
        return self.position[-1]

    def move(self):
        head = self.position[-1]
        if self.direction == 'up':
            self.position.append((head[0], head[1] - 1))
        elif self.direction == 'right':
            self.position.append((head[0] + 1, head[1]))
        elif self.direction == 'down':
            self.position.append((head[0], head[1] + 1))
        elif self.direction == 'left':
            self.position.append((head[0] - 1, head[1]))
        return self.position[-1]

    def remove_tail(self):
        tail = self.position.pop(0)
        return tail

    def set_direction(self, direction):
        self.direction = direction

    def get_direction(self):
        return self.direction

    def add_length(self):
        self.length += 1

    def get_length(self):
        return self.length
